package com.iteris.counterapp.screens.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ThemeMode(val icon: ImageVector, val name: String) {
    data object Light : ThemeMode(icon = Icons.Default.LightMode, name = "Light")
    data object Dark : ThemeMode(icon = Icons.Default.DarkMode, name = "Dark")
    data object System : ThemeMode(icon = Icons.Default.Android, name = "System Default")
}
