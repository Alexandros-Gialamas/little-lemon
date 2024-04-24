package com.alexandros.p.gialamas.littlelemon.screens

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.navigation.NavController
import com.alexandros.p.gialamas.littlelemon.DestinationsImpl
import com.alexandros.p.gialamas.littlelemon.R
import com.alexandros.p.gialamas.littlelemon.ui.theme.LittleLemonTheme

val backColor : Color = Color(0xFF495E57)
val buttonColor : Color = Color(0xFFF4CE14)
@Composable
fun OnBoarding(navController : NavController, sharedPreferences : SharedPreferences, context: Context){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "App Logo",
        modifier = Modifier
            .fillMaxWidth()
            .size(60.dp)
            .padding(8.dp)
        )
        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(backColor)
                .fillMaxWidth()
        ){
            Text(
                text = "Let`s get to know you",
                fontSize = 24.sp,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(32.dp)
                )
        }

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

        MyLabel(text = "First name")

        var firstName = myTextField(label = "First Name")

        Spacer(modifier = Modifier.height(16.dp))

        MyLabel(text = "Last name")

        var lastName = myTextField(label = "Last Name")

        Spacer(modifier = Modifier.height(16.dp))

        MyLabel(text = "Email")

        var email = myTextField(label = "Email")

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                    Toast.makeText(context, "Please fill all the fields", Toast.LENGTH_SHORT).show()
                } else {
                    with(sharedPreferences.edit()) {
                        putString("firstName", firstName)
                        putString("lastName", lastName)
                        putString("email", email)
                        putBoolean("isLoggedIn", true)
                        apply()
                    }
                    navController.navigate(DestinationsImpl.Home) {
                        popUpTo(DestinationsImpl.OnBoarding) { inclusive = true }
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Register",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(4.dp)
            )
        }
    }
}

@Composable
fun myTextField(label : String) : String {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            focusedLabelColor = backColor,
            focusedIndicatorColor = backColor
        ),
    )
    return text
}

@Composable
fun MyLabel(text : String){
    Text(
        text = text,
        textAlign = TextAlign.Start,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, 4.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun OnBoardingPreview() {
    LittleLemonTheme {
//        OnBoarding()
    }
}