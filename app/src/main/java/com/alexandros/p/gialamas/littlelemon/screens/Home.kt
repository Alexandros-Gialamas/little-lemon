package com.alexandros.p.gialamas.littlelemon.screens

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.room.Room
import com.alexandros.p.gialamas.littlelemon.DestinationsImpl
import com.alexandros.p.gialamas.littlelemon.MenuApi
import com.alexandros.p.gialamas.littlelemon.R
import com.alexandros.p.gialamas.littlelemon.data.MenuDatabase
import com.alexandros.p.gialamas.littlelemon.ui.theme.LittleLemonTheme
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch




@Composable
fun Home(navController: NavController, context: Context){
    val backColor : Color = Color(0xFF495E57)
    val buttonColor : Color = Color(0xFFF4CE14)
    val menuApi = MenuApi()
    val database by lazy { Room.databaseBuilder(context, MenuDatabase::class.java, "menu-db").build() }
    val databaseMenuItems by database.menuDao().getMenu().observeAsState(emptyList())
    var menuItems by remember { mutableStateOf(databaseMenuItems) }
    var searchPhrase by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth(0.2f)
                    .weight(0.2f)
                    .size(60.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "App Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(60.dp)
                        .padding(8.dp)
                )
            }
            // Profile Button
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier
//                    .fillMaxWidth(0.2f)
            ) {
                IconButton(
                    onClick = { navController.navigate(DestinationsImpl.Profile) },
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile",
                        modifier = Modifier
//                    .fillMaxWidth(0.2f)
                            .size(60.dp)
                            .padding(8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(backColor)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ){
            Text(
                text = "Little Lemon",
                color = buttonColor,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(start = 12.dp, top = 12.dp)
            )

            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ){
                Column {
                    Text(
                        text = "Chicago",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = "We are a family-owned\n Mediterranean restaurant,\n focused on traditional\n recipes served with a \nmodern twist\n",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(12.dp))
                }
                Spacer(modifier = Modifier.height(32.dp))
                Image(
                    painter = painterResource(id = R.drawable.hero_image),
                    contentDescription = "Hero Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 400.dp, height = 150.dp)
                )
            }


            OutlinedTextField(
                value = searchPhrase,
                onValueChange = { searchPhrase = it },
                label = { Text("Search") },
                colors = TextFieldDefaults.colors(
                    disabledContainerColor = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp)
            )
            LaunchedEffect(databaseMenuItems) {
                menuItems = databaseMenuItems
            }
            LaunchedEffect(searchPhrase) {
                menuItems = databaseMenuItems.filter {
                    it.title.contains(searchPhrase.text, ignoreCase = true)
                }
            }
        }
    }
    }

//    lifecycleScope.launch(Dispatchers.IO) {
//        if (databaseMenuItems.isEmpty()) {
//            val menuItemsNetwork = menuApi.getMenu()
//            saveMenuToDatabase(menuItemsNetwork)
//        }
//    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    LittleLemonTheme {
//        Home()
    }
}