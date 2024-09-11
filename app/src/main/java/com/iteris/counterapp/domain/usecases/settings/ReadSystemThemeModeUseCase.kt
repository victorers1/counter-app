package com.iteris.counterapp.domain.usecases.settings

import android.content.res.Configuration
import android.content.res.Resources

interface ReadSystemThemeModeUseCase {
    suspend fun isDarkMode(): Result<Boolean>
}

class ReadSystemThemeModeUseCaseImpl : ReadSystemThemeModeUseCase {
    override suspend fun isDarkMode(): Result<Boolean> {
        return when (Resources.getSystem().configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> Result.success(true)
            Configuration.UI_MODE_NIGHT_NO -> Result.success(false)
            else -> Result.success(false)
        }
    }
}
