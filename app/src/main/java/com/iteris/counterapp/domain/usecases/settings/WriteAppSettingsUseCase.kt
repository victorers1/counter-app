package com.iteris.counterapp.domain.usecases.settings

import com.iteris.counterapp.domain.entities.AppSettingsEntity
import com.iteris.counterapp.domain.repositories.AppSettingsRepository
import com.iteris.counterapp.ui.locale.LocaleModeEntity
import com.iteris.counterapp.ui.theme.ThemeModeEntity
import javax.inject.Inject

data class WriteAppSettingsParams(val themeMode: ThemeModeEntity, val locale: LocaleModeEntity)

interface WriteAppSettingsUseCase {
    suspend fun execute(params: WriteAppSettingsParams): Result<Unit>
}

class WriteAppSettingsUseCaseImpl @Inject constructor(private val repository: AppSettingsRepository) :
    WriteAppSettingsUseCase {
    override suspend fun execute(params: WriteAppSettingsParams): Result<Unit> {
        val entity = AppSettingsEntity(params.themeMode, params.locale)
        return repository.write(entity)
    }
}
