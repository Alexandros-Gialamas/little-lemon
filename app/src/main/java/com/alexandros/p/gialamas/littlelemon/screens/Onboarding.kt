package com.alexandros.p.gialamas.littlelemon.screens

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alexandros.p.gialamas.littlelemon.DestinationsImpl
import com.alexandros.p.gialamas.littlelemon.R
import com.alexandros.p.gialamas.littlelemon.ui.theme.LittleLemonColor


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
                .background(LittleLemonColor.green)
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

        var firstName = myTextField(label = "First Name", maxChars = 16)

        Spacer(modifier = Modifier.height(16.dp))

        MyLabel(text = "Last name")

        var lastName = myTextField(label = "Last Name", maxChars = 20)

        Spacer(modifier = Modifier.height(16.dp))

        MyLabel(text = "Email")

        var email = myTextField(label = "Email", maxChars = 50)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val message = context.getString(R.string.toast_message)
                if (firstName.isBlank() || lastName.isBlank() || email.isBlank()) {
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
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
                containerColor = LittleLemonColor.yellow,
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
fun myTextField(label : String, maxChars : Int) : String {
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { if (it.length <= maxChars) text = it },
        maxLines = 1,
        minLines = 1,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            focusedLabelColor = LittleLemonColor.green,
            focusedIndicatorColor = LittleLemonColor.green
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

