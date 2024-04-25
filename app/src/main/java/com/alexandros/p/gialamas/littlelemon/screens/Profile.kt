package com.alexandros.p.gialamas.littlelemon.screens

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alexandros.p.gialamas.littlelemon.DestinationsImpl
import com.alexandros.p.gialamas.littlelemon.R
import com.alexandros.p.gialamas.littlelemon.ui.theme.LittleLemonColor
import com.alexandros.p.gialamas.littlelemon.ui.theme.LittleLemonTheme

@Composable
fun Profile(navController : NavController, sharedPreferences : SharedPreferences) {
    var firstName by remember { mutableStateOf(sharedPreferences.getString("firstName", "") ?: "") }
    var lastName by remember { mutableStateOf(sharedPreferences.getString("lastName", "") ?: "") }
    var email by remember { mutableStateOf(sharedPreferences.getString("email", "") ?: "") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo), contentDescription = "App Logo",
            modifier = Modifier
                .fillMaxWidth()
                .size(60.dp)
                .padding(8.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Personal information",
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))

        Text(text = "First Name: $firstName")
        Text(text = "Last Name: $lastName")
        Text(text = "Email: $email")


        Button(onClick = {
            sharedPreferences.edit().clear().apply()
            navController.navigate(DestinationsImpl.OnBoarding) {
                popUpTo(DestinationsImpl.OnBoarding) { inclusive = true }
            }
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = LittleLemonColor.yellow,
                contentColor = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Log out",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(4.dp)
            )
        }
    }
    }



