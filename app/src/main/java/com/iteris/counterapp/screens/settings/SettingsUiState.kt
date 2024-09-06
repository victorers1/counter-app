package com.iteris.counterapp.screens.settings

import com.iteris.counterapp.ui.compose.errors.ErrorState
import com.iteris.counterapp.ui.theme.ThemeMode

data class SettingsUiState(
    val isLoading: Boolean = false,
    val themeMode: ThemeMode = ThemeMode.Light,
    val supportedThemeModes: List<ThemeMode> = listOf(
        ThemeMode.Light,
        ThemeMode.Dark,
        ThemeMode.System
    ),
    val showEraseDataConfirmDialog: Boolean = false,
    val error: ErrorState = ErrorState.NonError
)
