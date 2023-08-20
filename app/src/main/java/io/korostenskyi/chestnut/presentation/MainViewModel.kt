package io.korostenskyi.chestnut.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.korostenskyi.chestnut.domain.interactor.SettingsInteractor
import io.korostenskyi.chestnut.domain.model.ApplicationSettings
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(settingsInteractor: SettingsInteractor) : ViewModel() {

    val settingsFlow = settingsInteractor.settingsFlow
        .stateIn(viewModelScope, SharingStarted.Lazily, ApplicationSettings())
}
