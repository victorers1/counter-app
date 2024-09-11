package com.iteris.counterapp.data.repositories

import com.iteris.counterapp.data.models.toEntity
import com.iteris.counterapp.data.models.toModel
import com.iteris.counterapp.domain.datasources.AppSettingsDataSource
import com.iteris.counterapp.domain.entities.AppSettingsEntity
import com.iteris.counterapp.domain.repositories.AppSettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppSettingsRepositoryImpl @Inject constructor(
    private val appSettingsDataSource: AppSettingsDataSource
) : AppSettingsRepository {
    override suspend fun read(): Result<Flow<AppSettingsEntity>> {
        return try {
            Result.success(appSettingsDataSource.read().map { it.toEntity() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun write(data: AppSettingsEntity): Result<Unit> {
        return try {
            Result.success(appSettingsDataSource.write(data.toModel()))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
