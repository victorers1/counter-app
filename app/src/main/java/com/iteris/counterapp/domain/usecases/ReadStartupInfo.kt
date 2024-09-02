package com.iteris.counterapp.domain.usecases

import com.iteris.counterapp.domain.entities.AppStartupInfoEntity
import com.iteris.counterapp.domain.repositories.AppStartupInfoRepository
import javax.inject.Inject

interface ReadAppStartupInfoUseCase {
    suspend fun execute(): Result<AppStartupInfoEntity>
}

class ReadStartupInfoUseCaseImpl @Inject constructor(
    private val appStartupInfoRepository: AppStartupInfoRepository
) : ReadAppStartupInfoUseCase {
    override suspend fun execute(): Result<AppStartupInfoEntity> {
        return appStartupInfoRepository.read()
    }
}
