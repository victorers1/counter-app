package com.iteris.counterapp.screens.settings

import com.iteris.counterapp.ui.compose.errors.ErrorState

data class SettingsUiState(
    val isLoading: Boolean = false,
    val themeMode: ThemeMode = ThemeMode.Light,
    val error: ErrorState = ErrorState.NonError
)
