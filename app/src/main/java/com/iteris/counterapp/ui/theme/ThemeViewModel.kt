package com.iteris.counterapp.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iteris.counterapp.domain.usecases.settings.ReadAppSettingsUseCase
import com.iteris.counterapp.domain.usecases.settings.ReadSystemThemeModeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
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

    private val _isDarkTheme = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme.asStateFlow()

    init {

        viewModelScope.launch(Dispatchers.IO) {

            val resultSettings = readAppSettingsUseCase.execute()

            resultSettings.onSuccess { settingsFlow ->

                settingsFlow.collect { setting ->
                    _isDarkTheme.update {
                        if (setting.themeMode == ThemeModeEntity.System) {
                            isSystemSetToDarkMode()
                        } else {
                            setting.themeMode == ThemeModeEntity.Dark
                        }
                    }
                }
            }
        }
    }

    private suspend fun isSystemSetToDarkMode(): Boolean {
        val job = viewModelScope.async(Dispatchers.IO) {
            readSystemThemeModeUseCase.isDarkMode()
        }
        return job.await().getOrDefault(false)
    }
}
