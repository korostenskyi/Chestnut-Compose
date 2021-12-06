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
import io.korostenskyi.chestnut.presentation.utils.DEFAULT_PAGE_SIZE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieInteractor: MovieInteractor,
    private val navigationFlow: NavigationFlow
) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    val moviesStateFlow = Pager(PagingConfig(pageSize = DEFAULT_PAGE_SIZE)) {
        MoviePagingSource(movieInteractor, _isRefreshing)
    }.flow.stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty()).cachedIn(viewModelScope)

    fun openDetailsScreen(movieId: Int) {
        viewModelScope.launch {
            navigationFlow.navigate {
                fromHomeToDetails(movieId)
            }
        }
    }

    fun openSettingsScreen() {
        viewModelScope.launch {
            navigationFlow.navigate(Router::fromHomeToSettings)
        }
    }

    fun openFavoritesScreen() {
        viewModelScope.launch {
            navigationFlow.navigate(Router::fromHomeToFavorites)
        }
    }
}
