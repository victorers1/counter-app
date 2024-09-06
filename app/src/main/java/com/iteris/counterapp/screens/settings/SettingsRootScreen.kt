package com.iteris.counterapp.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.iteris.counterapp.screens.settings.components.AppVersion
import com.iteris.counterapp.screens.settings.components.EraseUserDataListItem
import com.iteris.counterapp.screens.settings.components.PoliciesListItem
import com.iteris.counterapp.screens.settings.components.ThemeSettingListItem
import com.iteris.counterapp.ui.compose.screen.BaseScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsRootScreen(settingsNavController: NavController) {
    val viewModel: SettingsViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()

    BaseScreen(onRetry = { /*TODO*/ }, onErrorClosed = { /*TODO*/ }) {
        Scaffold(topBar = { TopAppBar(title = { Text(text = "Settings") }) }) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(top = innerPadding.calculateTopPadding())
                    .verticalScroll(rememberScrollState())
            ) {
                ThemeSettingListItem(
                    selectedOption = uiState.value.themeMode,
                    options = uiState.value.supportedThemeModes,
                    onSelectedOption = { viewModel.onChangeThemeMode(it) },
                )

                PoliciesListItem(onClick = {
                    settingsNavController.navigate(SettingsTabScreens.Policies.route)
                })

                EraseUserDataListItem(onCLick = { viewModel.onEraseAllUserData() })

                // TODO: about the App

                AppVersion(
                    modifier = Modifier
                        .padding(top = 56.dp, bottom = innerPadding.calculateBottomPadding())
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
