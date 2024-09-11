package com.iteris.counterapp.domain.usecases.appstartinfo

import com.iteris.counterapp.domain.entities.AppStartupInfoEntity
import com.iteris.counterapp.domain.repositories.AppStartupInfoRepository
import java.util.Date
import javax.inject.Inject


data class WriteAppStartupInfoParams(val count: Int, val date: Date)

interface WriteAppStartupInfoUseCase {
    suspend fun execute(params: WriteAppStartupInfoParams): Result<Unit>
}

class WriteAppStartupInfoUseCaseImpl @Inject constructor(
    private val repository: AppStartupInfoRepository
) : WriteAppStartupInfoUseCase {

    override suspend fun execute(params: WriteAppStartupInfoParams): Result<Unit> {
        val entity = AppStartupInfoEntity(params.count, params.date)
        return repository.write(entity)
    }
}
