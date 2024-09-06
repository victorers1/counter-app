package com.iteris.counterapp.data.datasources

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.iteris.counterapp.data.models.AppStartupInfoLocalStorageModel
import com.iteris.counterapp.domain.datasources.AppStartupInfoDataSource
import kotlinx.coroutines.flow.first

class AppStartupInfoDataSourceImpl(private val context: Context) :
    AppStartupInfoDataSource {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("app_startup_info")

    companion object {
        private val STARTUP_COUNT = intPreferencesKey("STARTUP_COUNT")
        private val STARTUP_TIMESTAMP = longPreferencesKey("STARTUP_TIMESTAMP")
    }

    override suspend fun read(): AppStartupInfoLocalStorageModel {
        val prefs = context.dataStore.data.first()
        return AppStartupInfoLocalStorageModel(
            prefs[STARTUP_COUNT] ?: 0,
            prefs[STARTUP_TIMESTAMP] ?: 0L
        )
    }

    override suspend fun write(data: AppStartupInfoLocalStorageModel) {
        context.dataStore.edit {
            it[STARTUP_COUNT] = data.count ?: it[STARTUP_COUNT] ?: 0
            it[STARTUP_TIMESTAMP] = data.timestamp ?: it[STARTUP_TIMESTAMP] ?: 0L
        }
    }

    override suspend fun deleteAll() {
        context.dataStore.edit {
            it.clear()
        }
    }
}
