package com.iteris.counterapp.domain.repositories

import com.iteris.counterapp.domain.entities.AppSettingsEntity
import kotlinx.coroutines.flow.Flow

interface AppSettingsRepository {
    suspend fun read(): Result<Flow<AppSettingsEntity>>
    suspend fun write(data: AppSettingsEntity): Result<Unit>
}
