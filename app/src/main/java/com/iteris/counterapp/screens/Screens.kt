package com.iteris.counterapp.screens

import androidx.navigation.NavArgument

sealed class  Screens(val route: String, val navArgument: List<NavArgument> = emptyList()) {
    object Home : Screens(route = "home")
}
