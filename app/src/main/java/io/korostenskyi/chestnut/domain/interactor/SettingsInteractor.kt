package io.korostenskyi.chestnut.domain.interactor

import io.korostenskyi.chestnut.domain.model.ApplicationSettings
import kotlinx.coroutines.flow.Flow

interface SettingsInteractor {

    val settingsFlow: Flow<ApplicationSettings>

    suspend fun selectTheme(theme: ApplicationSettings.Theme)

    suspend fun reset()
}
