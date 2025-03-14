package com.iteris.counterapp.screens.settings

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.iteris.counterapp.screens.aboutus.AboutUsScreen
import com.iteris.counterapp.screens.policies.PoliciesScreen
import com.iteris.counterapp.screens.secret.SecretScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
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
            PoliciesScreen(onPop = { settingsNavHostController.popBackStack() })
        }

        composable(SettingsTabScreens.AboutUs.route) {
            AboutUsScreen(onPop = { settingsNavHostController.popBackStack() })
        }

        composable(SettingsTabScreens.Secret.route) {
            SecretScreen(onPop = { settingsNavHostController.popBackStack() })
        }
    }
}
