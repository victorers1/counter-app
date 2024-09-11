package com.iteris.counterapp.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iteris.counterapp.domain.usecases.settings.ReadAppSettingsUseCase
import com.iteris.counterapp.domain.usecases.settings.ReadSystemThemeModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val readAppSettingsUseCase: ReadAppSettingsUseCase,
    private val readSystemThemeModeUseCase: ReadSystemThemeModeUseCase,
) : ViewModel() {

    private val _isDarkThemeEnabled = MutableStateFlow(false)
    val isDarkThemeEnabled: StateFlow<Boolean> = _isDarkThemeEnabled.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            var systemIsSetToDarkMode = false

            val resultSettings = readAppSettingsUseCase.execute()
            readSystemThemeModeUseCase.isDarkMode().onSuccess {
                systemIsSetToDarkMode = it
            }

            resultSettings.onSuccess { settingsFlow ->
                settingsFlow
                    .collect { setting ->
                        _isDarkThemeEnabled.update {
                            if (setting.themeMode == ThemeModeEntity.System) {
                                systemIsSetToDarkMode
                            } else {
                                setting.themeMode == ThemeModeEntity.Dark
                            }
                        }
                    }
            }
        }
    }
}
