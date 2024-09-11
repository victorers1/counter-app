package com.iteris.counterapp.data.repositories

import com.iteris.counterapp.data.models.toEntity
import com.iteris.counterapp.data.models.toLocalStorageModel
import com.iteris.counterapp.domain.datasources.AppStartupInfoDataSource
import com.iteris.counterapp.domain.entities.AppStartupInfoEntity
import com.iteris.counterapp.domain.repositories.AppStartupInfoRepository
import javax.inject.Inject

class AppStartupInfoRepositoryImpl @Inject constructor(
    private val appStartupInfoDataSource: AppStartupInfoDataSource
) : AppStartupInfoRepository {

    override suspend fun read(): Result<AppStartupInfoEntity> {
        return try {
            Result.success(appStartupInfoDataSource.read().toEntity())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun write(data: AppStartupInfoEntity): Result<Unit> {
        return try {
            Result.success(appStartupInfoDataSource.write(data.toLocalStorageModel()))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun deleteAll(): Result<Unit> {
        return try {
            Result.success(appStartupInfoDataSource.deleteAll())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
