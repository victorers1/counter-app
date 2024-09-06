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
import com.iteris.counterapp.screens.home.HomeScreen
import com.iteris.counterapp.screens.settings.SettingsNavHost
import com.iteris.counterapp.ui.components.bottomappbar.BottomNavBar

@Composable
fun CounterApp() {
    val rootNavController: NavHostController = rememberNavController()

    Surface(color = MaterialTheme.colorScheme.background) {
        val bottomTabs = remember { listOf(BottomTabs.Home, BottomTabs.Settings) }
        var selectedTab by remember { mutableStateOf(bottomTabs.first()) }

        LaunchedEffect(rootNavController) {
            rootNavController.currentBackStackEntryFlow.collect { backStackEntry ->
                when (backStackEntry.destination.route) {
                    BottomTabs.Home.route -> selectedTab = BottomTabs.Home
                    BottomTabs.Settings.route -> selectedTab = BottomTabs.Settings
                }
            }
        }

        Column(modifier = Modifier.fillMaxSize()) {
            CounterRootAppNavHost(modifier = Modifier.weight(1f), navController = rootNavController)
            BottomNavBar(bottomTabs, selectedTab, onClickTab = { tab ->
                selectedTab = tab
                rootNavController.navigate(tab.route, navOptions = navOptions {
                    launchSingleTop = true
                    popUpTo(rootNavController.graph.startDestinationId)
                })
            })
        }
    }
}

@Composable
fun CounterRootAppNavHost(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = BottomTabs.Home.route
    ) {
        composable(route = BottomTabs.Home.route) {
            HomeScreen()
        }
        composable(route = BottomTabs.Settings.route) {
            SettingsNavHost()
        }
    }
}
