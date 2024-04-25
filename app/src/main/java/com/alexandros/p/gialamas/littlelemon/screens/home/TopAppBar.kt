package com.alexandros.p.gialamas.littlelemon.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.alexandros.p.gialamas.littlelemon.DestinationsImpl
import com.alexandros.p.gialamas.littlelemon.R
import com.alexandros.p.gialamas.littlelemon.ui.theme.LittleLemonColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopAppBar(navController : NavController,scaffoldState: ScaffoldState? = null, scope: CoroutineScope? = null) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { scope?.launch { scaffoldState?.drawerState?.open() } }
        ) {
            Icon(
                imageVector = Icons.Filled.Menu,
                contentDescription = "Menu Icon",
                modifier = Modifier.size(40.dp)
            )
        }
        Image(
            painter = rememberAsyncImagePainter(model =  R.drawable.logo),
            contentDescription = "Little Lemon Logo",
            modifier = Modifier
                .fillMaxWidth(0.7F)
                .size(60.dp)
                .padding(horizontal = 20.dp)

        )
            IconButton(
                onClick = { navController.navigate(DestinationsImpl.Profile) },
                modifier = Modifier
                    .border(border = BorderStroke(width = 2.dp, color = LittleLemonColor.charcoal), shape = RoundedCornerShape(30.dp))
                    .size(60.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile",
                )
            }


    }
}