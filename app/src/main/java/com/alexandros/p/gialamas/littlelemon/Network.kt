package com.alexandros.p.gialamas.littlelemon
import android.content.Context
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.LifecycleCoroutineScope
import com.alexandros.p.gialamas.littlelemon.data.MenuDatabase
import com.alexandros.p.gialamas.littlelemon.data.MenuItemEntity
import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

@Serializable
data class MenuNetwork(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>)

@Serializable
data class MenuItemNetwork(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("price")
    val price: String,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String
){
    fun passMenuToEntity() = MenuItemEntity(
        id = id,
        title = title,
        description = description,
        price = price,
        image = image,
        category = category
    )
}

class MenuApi {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
        }
    }

    suspend fun fetchMenu(): List<MenuItemNetwork> {
        val gson = Gson()
        val response : String = httpClient.get(
            "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json").body()
        val menuNetwork = gson.fromJson(response, MenuNetwork::class.java)
        return let { menuNetwork.menu } ?: emptyList()
    }

    fun saveMenuToDatabase(context: Context, menuItemsNetwork: List<MenuItemNetwork>) {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
        val database = MenuDatabase.getDatabase(context)
        val menuItemsRoom = menuItemsNetwork.map { it.passMenuToEntity() }
        scope.launch(Dispatchers.IO) {
            database.menuDao().insertMenu(*menuItemsRoom.toTypedArray())
        }
    }

}

