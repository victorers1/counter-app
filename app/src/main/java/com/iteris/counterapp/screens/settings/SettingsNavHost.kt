package com.iteris.counterapp.screens.settings

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iteris.counterapp.screens.policies.PoliciesScreen

@Composable
fun SettingsNavHost() {
    val settingsNavHostController: NavHostController = rememberNavController()

    NavHost(
        navController = settingsNavHostController,
        startDestination = SettingsTabScreens.SettingsRoot.route
    ) {
        composable(SettingsTabScreens.SettingsRoot.route) {
            SettingsRootScreen(settingsNavHostController)
        }

        composable(SettingsTabScreens.Policies.route) {
            PoliciesScreen(onPop = {
                settingsNavHostController.popBackStack()
            })
        }
    }
}
