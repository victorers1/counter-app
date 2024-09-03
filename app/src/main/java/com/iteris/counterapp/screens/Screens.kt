package com.iteris.counterapp.screens

sealed class Screens(val route: String) {
    data object Home : Screens(route = "home")
    data object Settings : Screens(route = "settings")
}
