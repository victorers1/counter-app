package com.iteris.counterapp.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iteris.counterapp.domain.entities.settings.AppSettingsEntity
import com.iteris.counterapp.domain.usecases.settings.ReadAppSettingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

/// A viewmodel just for the global Theme management.
@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val readAppSettingsUseCase: ReadAppSettingsUseCase,
) : ViewModel() {
    private val _themeMode: MutableStateFlow<ThemeModeEntity> =
        MutableStateFlow(ThemeModeEntity.System)
    val themeMode: StateFlow<ThemeModeEntity> = _themeMode.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val resultSettings = readAppSettingsUseCase.execute()

            resultSettings.onSuccess { settingsFlow: Flow<AppSettingsEntity> ->
                settingsFlow.collect { setting: AppSettingsEntity ->
                    _themeMode.update { setting.themeMode }
                }
            }
        }
    }
}
