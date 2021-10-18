package io.korostenskyi.chestnut.presentation.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.korostenskyi.chestnut.domain.interactor.SettingsInteractor
import io.korostenskyi.chestnut.domain.model.ApplicationSettings
import io.korostenskyi.chestnut.presentation.navigation.NavigationFlow
import io.korostenskyi.chestnut.presentation.navigation.Router
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val navigationFlow: NavigationFlow,
    private val interactor: SettingsInteractor
) : ViewModel() {

    private val _settingsFlow = MutableStateFlow<SettingsState>(SettingsState.Idle)
    val settingsFlow = _settingsFlow.asStateFlow()

    init {
        viewModelScope.launch {
            interactor.settingsFlow.collect { settings ->
                val state = SettingsState.Loaded(settings)
                _settingsFlow.emit(state)
            }
        }
    }

    fun back() {
        viewModelScope.launch {
            navigationFlow.navigate(Router::back)
        }
    }

    fun selectTheme(theme: ApplicationSettings.Theme) {
        viewModelScope.launch {
            interactor.selectTheme(theme)
        }
    }

    fun reset() {
        viewModelScope.launch {
            interactor.reset()
        }
    }
}
