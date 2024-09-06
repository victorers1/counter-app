package com.iteris.counterapp.screens.settings

sealed class SettingsTabScreens(val route: String) {
    data object SettingsRoot : SettingsTabScreens(route = "root")
    data object Policies : SettingsTabScreens(route = "policies")
}