package io.korostenskyi.chestnut.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.korostenskyi.chestnut.domain.interactor.MovieInteractor
import io.korostenskyi.chestnut.presentation.navigation.NavigationFlow
import io.korostenskyi.chestnut.presentation.navigation.Router
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieInteractor: MovieInteractor,
    private val navigationFlow: NavigationFlow
) : ViewModel() {

    private val _sideEffectFlow = MutableSharedFlow<HomeSideEffect>()
    val sideEffectFlow = _sideEffectFlow.asSharedFlow()

    val moviesStateFlow = Pager(PagingConfig(pageSize = 20)) {
        MoviePagingSource(movieInteractor)
    }.flow.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty()).cachedIn(viewModelScope)

    fun openDetailsScreen(movieId: Long) {
        viewModelScope.launch {
            navigationFlow.navigate {
                fromPopularToDetails(movieId)
            }
        }
    }
}
