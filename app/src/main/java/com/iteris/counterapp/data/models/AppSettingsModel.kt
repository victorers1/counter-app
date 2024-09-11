package com.iteris.counterapp.data.models

import com.iteris.counterapp.domain.entities.AppSettingsEntity
import com.iteris.counterapp.ui.locale.LocaleModeEntity
import com.iteris.counterapp.ui.theme.ThemeModeEntity

data class AppSettingsModel(val themeModeId: Int?, val language: String?)

fun AppSettingsModel.toEntity() = AppSettingsEntity(
    getThemeModeById(this.themeModeId ?: ThemeModeEntity.System.id),
    getLocaleModeByLanguage(this.language ?: LocaleModeEntity.English.language)
)

fun AppSettingsEntity.toModel() = AppSettingsModel(
    this.themeMode.id,
    this.locale.language,
)

private fun getThemeModeById(id: Int): ThemeModeEntity {
    val allThemes = listOf(ThemeModeEntity.Light, ThemeModeEntity.Dark, ThemeModeEntity.System)
    return allThemes.first { it.id == id }
}

private fun getLocaleModeByLanguage(language: String): LocaleModeEntity {
    val allLocales =
        listOf(LocaleModeEntity.English, LocaleModeEntity.Spanish, LocaleModeEntity.Portuguese)
    return allLocales.first { it.language == language }
}
