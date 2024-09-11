package com.iteris.counterapp.domain.usecases.settings

import com.iteris.counterapp.domain.entities.AppSettingsEntity
import com.iteris.counterapp.domain.repositories.AppSettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface ReadAppSettingsUseCase {
    suspend fun execute(): Result<Flow<AppSettingsEntity>>
}

class ReadAppSettingsUseCaseImpl @Inject constructor(private val repository: AppSettingsRepository) :
    ReadAppSettingsUseCase {
    override suspend fun execute(): Result<Flow<AppSettingsEntity>> {
        return repository.read()
    }
}
