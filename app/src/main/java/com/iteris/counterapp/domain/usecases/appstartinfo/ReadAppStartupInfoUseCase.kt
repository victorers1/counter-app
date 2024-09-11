package com.iteris.counterapp.domain.usecases.appstartinfo

import com.iteris.counterapp.domain.entities.AppStartupInfoEntity
import com.iteris.counterapp.domain.repositories.AppStartupInfoRepository
import javax.inject.Inject

interface ReadAppStartupInfoUseCase {
    suspend fun execute(): Result<AppStartupInfoEntity>
}

class ReadAppStartupInfoUseCaseImpl @Inject constructor(
    private val repository: AppStartupInfoRepository
) : ReadAppStartupInfoUseCase {
    override suspend fun execute(): Result<AppStartupInfoEntity> {
        return repository.read()
    }
}
