package com.iteris.counterapp.screens.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
//    private val changeAppThemeUseCase: ChangeAppThemeUseCase
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    fun onChangeThemeMode(newThemeMode: ThemeMode) {
        _uiState.update {
            it.copy(
                themeMode = newThemeMode
            )
        }
    }

    fun onEraseAllUserData() {
    }
}
