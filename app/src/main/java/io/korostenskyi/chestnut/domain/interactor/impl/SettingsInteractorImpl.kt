package io.korostenskyi.chestnut.domain.interactor.impl

import io.korostenskyi.chestnut.domain.interactor.SettingsInteractor
import io.korostenskyi.chestnut.domain.model.ApplicationSettings
import io.korostenskyi.chestnut.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsInteractorImpl @Inject constructor(
    private val repository: SettingsRepository
) : SettingsInteractor {

    override val settingsFlow: Flow<ApplicationSettings> = repository.settingsFlow

    override suspend fun selectTheme(theme: ApplicationSettings.Theme) {
        repository.selectTheme(theme)
    }
}
