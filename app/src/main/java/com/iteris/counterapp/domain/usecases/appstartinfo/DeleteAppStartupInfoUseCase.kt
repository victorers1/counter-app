package com.iteris.counterapp.domain.usecases.appstartinfo

import com.iteris.counterapp.domain.repositories.AppStartupInfoRepository
import javax.inject.Inject

interface DeleteAppStartupInfoUseCase {
    suspend fun execute(): Result<Unit>
}

class DeleteAppStartupInfoUseCaseImpl @Inject constructor(
    private val repository: AppStartupInfoRepository
) : DeleteAppStartupInfoUseCase {
    override suspend fun execute(): Result<Unit> {
        return repository.deleteAll()
    }
}
