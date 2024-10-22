package com.iteris.counterapp.screens.settings

sealed class SettingsTabScreens(val route: String) {
    data object SettingsRoot : SettingsTabScreens(route = "root")
    data object Policies : SettingsTabScreens(route = "policies")
    data object AboutUs : SettingsTabScreens(route = "about-us")
    data object Secret : SettingsTabScreens(route = "secret-screen")
}
