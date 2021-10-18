package io.korostenskyi.chestnut.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import io.korostenskyi.chestnut.domain.model.ApplicationSettings
import io.korostenskyi.chestnut.domain.repository.SettingsRepository
import io.korostenskyi.chestnut.extensions.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(context: Context) : SettingsRepository {

    private val dataStore = context.dataStore

    override val settingsFlow: Flow<ApplicationSettings> = dataStore.data.map { preferences ->
        val theme = when (preferences[THEME_KEY]) {
            ApplicationSettings.Theme.SYSTEM.name -> ApplicationSettings.Theme.SYSTEM
            ApplicationSettings.Theme.LIGHT.name -> ApplicationSettings.Theme.LIGHT
            ApplicationSettings.Theme.DARK.name -> ApplicationSettings.Theme.DARK
            ApplicationSettings.Theme.PURPLE.name -> ApplicationSettings.Theme.PURPLE
            else -> ApplicationSettings.Theme.SYSTEM
        }
        return@map ApplicationSettings(theme)
    }

    override suspend fun selectTheme(theme: ApplicationSettings.Theme) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme.name
        }
    }

    override suspend fun reset() {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = ApplicationSettings.Theme.SYSTEM.name
        }
    }

    companion object {
        val THEME_KEY = stringPreferencesKey("theme")
    }
}