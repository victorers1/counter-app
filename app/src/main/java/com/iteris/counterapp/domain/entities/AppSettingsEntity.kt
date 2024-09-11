package com.iteris.counterapp.domain.entities

import com.iteris.counterapp.ui.locale.LocaleModeEntity
import com.iteris.counterapp.ui.theme.ThemeModeEntity

data class AppSettingsEntity(val themeMode: ThemeModeEntity, val locale: LocaleModeEntity)