package com.iteris.counterapp.domain.repositories

import com.iteris.counterapp.domain.entities.AppStartupInfoEntity

interface AppStartupInfoRepository {
    suspend fun read(): Result<AppStartupInfoEntity>
    suspend fun write(data: AppStartupInfoEntity) : Result<Unit>
    suspend fun deleteAll(): Result<Unit>
}
