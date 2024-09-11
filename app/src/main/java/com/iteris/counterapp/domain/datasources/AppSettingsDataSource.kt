package com.iteris.counterapp.domain.datasources

import com.iteris.counterapp.data.models.AppSettingsModel
import kotlinx.coroutines.flow.Flow

interface AppSettingsDataSource {
    suspend fun read(): Flow<AppSettingsModel>
    suspend fun write(data: AppSettingsModel)
}
