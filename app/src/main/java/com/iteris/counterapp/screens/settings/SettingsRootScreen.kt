package com.iteris.counterapp.screens.settings

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.iteris.counterapp.core.extensions.getActivityOrNull
import com.iteris.counterapp.domain.entities.biometric_auth.BiometricAuthResultEntity
import com.iteris.counterapp.screens.settings.components.AboutUsListItem
import com.iteris.counterapp.screens.settings.components.AppVersion
import com.iteris.counterapp.screens.settings.components.EraseAppDataConfirmDialog
import com.iteris.counterapp.screens.settings.components.EraseUserDataListItem
import com.iteris.counterapp.screens.settings.components.OpenSecretListItem
import com.iteris.counterapp.screens.settings.components.PoliciesListItem
import com.iteris.counterapp.screens.settings.components.ThemeSettingListItem
import com.iteris.counterapp.ui.components.toasts.showInfoToast
import com.iteris.counterapp.ui.compose.screen.BaseScreen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@ExperimentalCoroutinesApi
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsRootScreen(settingsNavController: NavController) {
    val viewModel: SettingsViewModel = hiltViewModel()
    val uiState: State<SettingsUiState> = viewModel.uiState.collectAsStateWithLifecycle()
    val context: Context = LocalContext.current
    val activity: AppCompatActivity? = context.getActivityOrNull()
    val enrollLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult(),
        onResult = {
            Log.d("biometric_auth", "result: $it")
        }
    )

    LaunchedEffect(Unit) {
        viewModel.authPromptResults.collect {
            Log.d("biometric_auth", "collected value $it")

            when (it) {
                BiometricAuthResultEntity.AuthenticationSucceeded -> {
                    settingsNavController.navigate(SettingsTabScreens.Secret.route)
                }

                BiometricAuthResultEntity.UserNotEnrolled -> {
                    if (Build.VERSION.SDK_INT >= 30) {
                        val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                            putExtra(
                                Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                            )
                        }
                        enrollLauncher.launch(enrollIntent)
                    }
                }

                else -> {
                    launch(Dispatchers.IO) { showInfoToast(context, it.message) }
                }
            }
        }
    }

    BaseScreen(onRetry = { /*TODO*/ }, onErrorClosed = { /*TODO*/ }) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "Settings") }, colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    )
                )
            },
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(top = innerPadding.calculateTopPadding())
                    .verticalScroll(rememberScrollState())
            ) {


                Spacer(modifier = Modifier.height(16.dp))

                if (uiState.value.showEraseDataConfirmDialog) EraseAppDataConfirmDialog(onDismiss = { viewModel.toggleEraseDataConfirmDialog() },
                    onConfim = { viewModel.onEraseAllUserData() })

                ThemeSettingListItem(
                    selectedOption = uiState.value.themeModeEntity,
                    options = uiState.value.supportedThemeModeEntities,
                    onSelectedOption = { viewModel.onChangeThemeMode(it) },
                )

                PoliciesListItem(onClick = {
                    settingsNavController.navigate(SettingsTabScreens.Policies.route)
                })

                EraseUserDataListItem(onClick = { viewModel.toggleEraseDataConfirmDialog() })

                AboutUsListItem(onClick = {
                    settingsNavController.navigate(SettingsTabScreens.AboutUs.route)
                })

                OpenSecretListItem(onClick = {
                    if (activity != null) {
                        viewModel.showBiometricAuthPrompt(activity, context)
                    } else {
                        runBlocking(Dispatchers.IO) {
                            showInfoToast(context, "Something went wrong. Try again later")
                        }
                    }
                })

                AppVersion(
                    modifier = Modifier
                        .padding(
                            top = 56.dp, bottom = innerPadding.calculateBottomPadding()
                        )
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
