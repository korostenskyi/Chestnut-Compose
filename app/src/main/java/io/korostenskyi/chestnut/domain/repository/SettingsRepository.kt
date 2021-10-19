package io.korostenskyi.chestnut.domain.repository

import io.korostenskyi.chestnut.domain.model.ApplicationSettings
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    val settingsFlow: Flow<ApplicationSettings>

    suspend fun selectTheme(theme: ApplicationSettings.Theme)

    suspend fun reset()
}
