package io.korostenskyi.chestnut.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.korostenskyi.chestnut.domain.interactor.SettingsInteractor
import io.korostenskyi.chestnut.domain.model.ApplicationSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(settingsInteractor: SettingsInteractor) : ViewModel() {

    private val _settingsFlow = MutableStateFlow<ApplicationSettings>(
        ApplicationSettings(ApplicationSettings.Theme.SYSTEM)
    )
    val settingsFlow = _settingsFlow.asStateFlow()

    init {
        viewModelScope.launch {
            settingsInteractor.settingsFlow.collect {
                _settingsFlow.emit(it)
            }
        }
    }
}
