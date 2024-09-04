package com.iteris.counterapp.screens.settings

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.iteris.counterapp.screens.settings.components.AppVersion
import com.iteris.counterapp.screens.settings.components.ThemeSettingListItem
import com.iteris.counterapp.ui.compose.screen.BaseScreen
import com.iteris.counterapp.ui.theme.CounterAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {

    val themes = listOf(ThemeMode.Dark, ThemeMode.Light, ThemeMode.System)

    BaseScreen(onRetry = { /*TODO*/ }, onErrorClosed = { /*TODO*/ }) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Settings") })
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                ThemeSettingListItem(
                    selectedOption = ThemeMode.Dark,
                    options = themes,
                    onSelectedOption = {},
                )
                Spacer(modifier = Modifier.height(24.dp))
                AppVersion()
            }
        }
    }
}


@Preview
@Composable
private fun SettingsPrevLight() {
    CounterAppTheme {
        SettingsScreen()
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SettingsPrevDark() {
    CounterAppTheme {
        SettingsScreen()
    }
}
