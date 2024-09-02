package com.iteris.counterapp

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iteris.counterapp.screens.Screens
import com.iteris.counterapp.screens.home.HomeScreen

@Composable
fun CounterApp() {
    val navController = rememberNavController()
    CounterAppNavHost(navController = navController)
}

@Composable
fun CounterAppNavHost(navController: NavHostController) {
    val activity = (LocalContext.current as Activity)

    NavHost(navController = navController, startDestination = Screens.Home.route) {
        composable(route = Screens.Home.route) {
            HomeScreen()
        }
    }
}
