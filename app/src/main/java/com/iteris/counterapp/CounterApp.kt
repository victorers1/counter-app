package com.iteris.counterapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.iteris.counterapp.screens.BottomTabs
import com.iteris.counterapp.screens.Screens
import com.iteris.counterapp.screens.home.HomeScreen
import com.iteris.counterapp.screens.settings.SettingsScreen
import com.iteris.counterapp.ui.components.bottomappbar.BottomNavBar

@Composable
fun CounterApp(navController: NavHostController = rememberNavController()) {
    Surface(color = MaterialTheme.colorScheme.background) {
        val bottomTabs = remember { listOf(BottomTabs.HomeTab, BottomTabs.SettingsTab) }
        var selectedTab by remember { mutableStateOf(bottomTabs.first()) }

        LaunchedEffect(navController) {
            navController.currentBackStackEntryFlow.collect { backStackEntry ->
                when (backStackEntry.destination.route) {
                    BottomTabs.HomeTab.route -> selectedTab = BottomTabs.HomeTab
                    BottomTabs.SettingsTab.route -> selectedTab = BottomTabs.SettingsTab
                }
            }
        }

        Column(modifier = Modifier.fillMaxSize()) {
            CounterAppNavHost(modifier = Modifier.weight(1f), navController = navController)
            BottomNavBar(bottomTabs, selectedTab, onClickTab = { tab ->
                selectedTab = tab
                navController.navigate(tab.route, navOptions = navOptions {
                    launchSingleTop = true
                    popUpTo(navController.graph.startDestinationId)
                })
            })
        }
    }
}

@Composable
fun CounterAppNavHost(
    modifier: Modifier = Modifier, navController: NavHostController
) {
    NavHost(
        modifier = modifier, navController = navController, startDestination = Screens.Home.route
    ) {
        composable(route = Screens.Home.route) {
            HomeScreen()
        }
        composable(route = Screens.Settings.route) {
            SettingsScreen()
        }
    }
}
