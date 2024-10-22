package com.iteris.counterapp.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.ui.graphics.vector.ImageVector

/// These are the options the user have in the setting screen to change the App's Theme
sealed class ThemeModeEntity(val id: Int, val icon: ImageVector, val name: String) {
    data object Light : ThemeModeEntity(id = 0, icon = Icons.Default.LightMode, name = "Light")
    data object Dark : ThemeModeEntity(id = 1, icon = Icons.Default.DarkMode, name = "Dark")
    data object System :
        ThemeModeEntity(id = 2, icon = Icons.Default.Android, name = "System Default")
}
