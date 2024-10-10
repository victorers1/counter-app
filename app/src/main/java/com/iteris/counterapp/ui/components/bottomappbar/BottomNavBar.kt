package com.iteris.counterapp.ui.components.bottomappbar

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import com.iteris.counterapp.screens.BottomTabs

@Composable
fun BottomNavBar(
    bottomTabs: List<BottomTabs>, selectedTab: BottomTabs, onClickTab: (BottomTabs) -> Unit
) {
    val fonsScale = LocalDensity.current.fontScale
    val fontScaleThreshold = 1.3

    BottomAppBar(
        actions = {
            bottomTabs.map { tab ->
                val isSelected = selectedTab == tab
                NavigationBarItem(
                    label = { if (fonsScale < fontScaleThreshold) Text(text = tab.label) },
                    selected = isSelected,
                    onClick = { onClickTab(tab) },
                    icon = {
                        Icon(
                            imageVector = if (isSelected) tab.selectedIcon else tab.unselectedIcon,
                            contentDescription = tab.label
                        )
                    },
                )
            }
        },
    )
}
