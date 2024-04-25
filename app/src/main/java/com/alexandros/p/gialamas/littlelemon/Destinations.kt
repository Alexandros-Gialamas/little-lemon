package com.alexandros.p.gialamas.littlelemon

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alexandros.p.gialamas.littlelemon.screens.Home
import com.alexandros.p.gialamas.littlelemon.screens.OnBoarding
import com.alexandros.p.gialamas.littlelemon.screens.Profile

interface Destinations{
    val OnBoarding : String
    val Home : String
    val Profile : String
}

object DestinationsImpl : Destinations {
    override val OnBoarding = "onboarding"
    override val Home = "home"
    override val Profile = "profile"
}

@Composable
fun NavigationComposable(context: Context) {
    val navController = rememberNavController()
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("app_data", Context.MODE_PRIVATE)
    var isLoggedIn by remember { mutableStateOf(sharedPreferences.getBoolean("isLoggedIn", false)) }

    val startDestination = if (isLoggedIn) DestinationsImpl.Home else DestinationsImpl.OnBoarding

    val (selectedCategories, setSelectedCategories) = remember { mutableStateOf(setOf<FilteredCategory>())}

    NavHost(navController = navController, startDestination = startDestination) {
        composable(DestinationsImpl.OnBoarding) {  OnBoarding(navController, sharedPreferences, context) }
        composable(DestinationsImpl.Home) { Home(navController, context, selectedCategories, setSelectedCategories) }
        composable(DestinationsImpl.Profile) { Profile(navController, sharedPreferences) }
    }
}