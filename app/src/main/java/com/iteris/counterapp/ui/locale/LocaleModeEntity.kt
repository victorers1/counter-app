package com.iteris.counterapp.ui.locale

sealed class LocaleModeEntity(val language: String) {
    data object English : LocaleModeEntity(language = "en")
    data object Portuguese : LocaleModeEntity(language = "pt")
    data object Spanish : LocaleModeEntity(language = "es")
}
