package com.alexandros.p.gialamas.littlelemon.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.room.Room
import com.alexandros.p.gialamas.littlelemon.FilteredCategory
import com.alexandros.p.gialamas.littlelemon.MainActivity
import com.alexandros.p.gialamas.littlelemon.MenuApi
import com.alexandros.p.gialamas.littlelemon.MenuItemNetwork
import com.alexandros.p.gialamas.littlelemon.data.MenuDatabase
import com.alexandros.p.gialamas.littlelemon.screens.home.LowerPanel
import com.alexandros.p.gialamas.littlelemon.screens.home.MiddlePanel
import com.alexandros.p.gialamas.littlelemon.screens.home.TopAppBar
import com.alexandros.p.gialamas.littlelemon.screens.home.UpperPanel
import com.alexandros.p.gialamas.littlelemon.ui.theme.LittleLemonTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


@Composable
fun Home(
    navController: NavController,
    context: Context,
    selectedCategories : Set<FilteredCategory>,
    setSelectedCategories : (Set<FilteredCategory>) -> Unit
) {
    val scope = rememberCoroutineScope()
    val menuApi = MenuApi()
    val database = MenuDatabase.getDatabase(context)
    val databaseMenuItems by database.menuDao().getMenu().observeAsState(emptyList())
    val searchPhraseFlow = remember { MutableStateFlow(TextFieldValue("")) }
    var menuItems by remember { mutableStateOf(databaseMenuItems) }
//        menuItems.sortedBy { it.title }.filter { it.title.contains(searchPhrase.text, ignoreCase = true) }




    Column {
        TopAppBar(navController)
        UpperPanel(searchPhraseFlow = searchPhraseFlow)
        MiddlePanel(
            selectedCategories = selectedCategories,
            setSelectedCategories = setSelectedCategories
        )
        LowerPanel(
            navController = navController,
            menuItems = menuItems,
            selectedCategories = selectedCategories,
            searchPhraseFlow = searchPhraseFlow
        )
    }


    LaunchedEffect(databaseMenuItems) {
        menuItems = databaseMenuItems

        scope.launch(Dispatchers.IO) {
            if (databaseMenuItems.isEmpty()) {
                val menuItemsNetwork = menuApi.fetchMenu()
                menuApi.saveMenuToDatabase(context, menuItemsNetwork)
            }
        }
    }
}




