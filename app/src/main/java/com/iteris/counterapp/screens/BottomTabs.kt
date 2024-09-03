package com.iteris.counterapp.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomTabs(
    val selectedIcon: ImageVector = Icons.Default.Error,
    val unselectedIcon: ImageVector = Icons.Default.Error,
    val label: String = "Label",
    val route: String = ""
) {
    data object HomeTab : BottomTabs(
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        label = "Home",
        route = Screens.Home.route
    )

    data object SettingsTab : BottomTabs(
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        label = "Settings",
        route = Screens.Settings.route
    )
}
