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
        private val STARTUP_COUNT_KEY = intPreferencesKey("STARTUP_COUNT")
        private val STARTUP_TIMESTAMP_KEY = longPreferencesKey("STARTUP_TIMESTAMP")
    }

    override suspend fun read(): AppStartupInfoLocalStorageModel {
        val prefs = context.dataStore.data.first()
        return AppStartupInfoLocalStorageModel(
            prefs[STARTUP_COUNT_KEY] ?: 0,
            prefs[STARTUP_TIMESTAMP_KEY] ?: 0L
        )
    }

    override suspend fun write(data: AppStartupInfoLocalStorageModel) {
        context.dataStore.edit {
            it[STARTUP_COUNT_KEY] = data.count ?: it[STARTUP_COUNT_KEY] ?: 0
            it[STARTUP_TIMESTAMP_KEY] = data.timestamp ?: it[STARTUP_TIMESTAMP_KEY] ?: 0L
        }
    }

    override suspend fun deleteAll() {
        context.dataStore.edit {
            it.clear()
        }
    }
}
