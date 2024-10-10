package com.iteris.counterapp.screens.settings

import com.iteris.counterapp.ui.compose.errors.ErrorState
import com.iteris.counterapp.ui.locale.LocaleModeEntity
import com.iteris.counterapp.ui.theme.ThemeModeEntity

data class SettingsUiState(
    val isLoading: Boolean = false,
    val locale: LocaleModeEntity = LocaleModeEntity.English,
    val supportedLocaleModes: List<LocaleModeEntity> = listOf(
        LocaleModeEntity.English,
        LocaleModeEntity.Portuguese,
        LocaleModeEntity.Spanish,
    ),
    val themeModeEntity: ThemeModeEntity = ThemeModeEntity.System,
    val supportedThemeModeEntities: List<ThemeModeEntity> = listOf(
        ThemeModeEntity.Light, ThemeModeEntity.Dark, ThemeModeEntity.System
    ),
    val showEraseDataConfirmDialog: Boolean = false,
    val error: ErrorState = ErrorState.NonError
)
