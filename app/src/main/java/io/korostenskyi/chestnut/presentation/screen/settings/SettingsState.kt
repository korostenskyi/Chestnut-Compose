package io.korostenskyi.chestnut.presentation.screen.settings

import io.korostenskyi.chestnut.domain.model.ApplicationSettings

sealed class SettingsState {

    object Idle : SettingsState()

    data class Loaded(val settings: ApplicationSettings) : SettingsState()
}
