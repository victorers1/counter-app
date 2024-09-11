package com.iteris.counterapp.data.datasources

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.iteris.counterapp.R
import com.iteris.counterapp.data.models.AppSettingsModel
import com.iteris.counterapp.domain.datasources.AppSettingsDataSource
import com.iteris.counterapp.ui.theme.ThemeModeEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppSettingsDataSourceImpl(private val context: Context) : AppSettingsDataSource {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        context.getString(
            R.string.app_settings_data_store_name
        )
    )

    companion object {
        private val THEME_MODE_KEY = intPreferencesKey("THEME_MODE_ID")
        private val LANGUAGE_KEY = stringPreferencesKey("LANGUAGE_ID")
    }

    override suspend fun read(): Flow<AppSettingsModel> = context.dataStore.data.map { prefs ->
        AppSettingsModel(
            prefs[THEME_MODE_KEY] ?: ThemeModeEntity.System.id,
            prefs[LANGUAGE_KEY] ?: context.getString(R.string.default_language_code)
        )
    }

    override suspend fun write(data: AppSettingsModel) {
        context.dataStore.edit {
            it[THEME_MODE_KEY] = data.themeModeId ?: ThemeModeEntity.System.id
            it[LANGUAGE_KEY] = data.language ?: context.getString(R.string.default_language_code)
        }
    }
}
