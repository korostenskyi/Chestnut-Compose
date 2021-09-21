package io.korostenskyi.chestnut.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.korostenskyi.chestnut.domain.interactor.MovieInteractor
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieInteractor: MovieInteractor
) : ViewModel() {

    private val _sideEffectFlow = MutableSharedFlow<HomeSideEffect>()
    val sideEffectFlow = _sideEffectFlow.asSharedFlow()

    val moviesStateFlow = Pager(PagingConfig(pageSize = 20)) {
        MoviePagingSource(movieInteractor)
    }.flow.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}
