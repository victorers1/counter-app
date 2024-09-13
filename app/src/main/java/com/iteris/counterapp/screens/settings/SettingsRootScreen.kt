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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.iteris.counterapp.screens.settings.components.AboutUsListItem
import com.iteris.counterapp.screens.settings.components.AppVersion
import com.iteris.counterapp.screens.settings.components.EraseAppDataConfirmDialog
import com.iteris.counterapp.screens.settings.components.EraseUserDataListItem
import com.iteris.counterapp.screens.settings.components.PoliciesListItem
import com.iteris.counterapp.screens.settings.components.ThemeSettingListItem
import com.iteris.counterapp.ui.compose.screen.BaseScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsRootScreen(settingsNavController: NavController) {
    val viewModel: SettingsViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    BaseScreen(onRetry = { /*TODO*/ }, onErrorClosed = { /*TODO*/ }) {
        Scaffold(topBar = { TopAppBar(title = { Text(text = "Settings") }) }) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(top = innerPadding.calculateTopPadding())
                    .verticalScroll(rememberScrollState())
            ) {

                if (uiState.value.showEraseDataConfirmDialog)
                    EraseAppDataConfirmDialog(
                        onDismiss = { viewModel.toggleEraseDataConfirmDialog() },
                        onConfim = { viewModel.onEraseAllUserData() }
                    )

                ThemeSettingListItem(
                    selectedOption = uiState.value.themeModeEntity,
                    options = uiState.value.supportedThemeModeEntities,
                    onSelectedOption = { viewModel.onChangeThemeMode(it) },
                )

                PoliciesListItem(onClick = {
                    settingsNavController.navigate(SettingsTabScreens.Policies.route)
                })

                EraseUserDataListItem(onCLick = { viewModel.toggleEraseDataConfirmDialog() })

                AboutUsListItem(onCLick = {
                    settingsNavController.navigate(SettingsTabScreens.AboutUs.route)
                })
                // TODO: Animations on counter created and deletion

                // TODO: Change Language

                // TODO: error treatment

                // TODO: securiry features

                // TODO: about the App page with internet signal status just for fun

                // TODO: Ascessibility: treat text size config

                // TODO: Accessibility: adaptable UI to foldable big screen

                // TODO: change status bar color according to the theme mode

                AppVersion(
                    modifier = Modifier
                        .padding(top = 56.dp, bottom = innerPadding.calculateBottomPadding())
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
