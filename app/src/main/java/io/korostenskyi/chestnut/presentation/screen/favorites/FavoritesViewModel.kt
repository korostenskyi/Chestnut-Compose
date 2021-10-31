package io.korostenskyi.chestnut.presentation.screen.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.korostenskyi.chestnut.domain.interactor.MovieInteractor
import io.korostenskyi.chestnut.presentation.navigation.NavigationFlow
import io.korostenskyi.chestnut.presentation.navigation.Router
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val navigationFlow: NavigationFlow,
    movieInteractor: MovieInteractor
) : ViewModel() {

    val favoriteMovies = movieInteractor.favoriteMoviesFlow.map {
        movieInteractor.retrieveMoviesByIds(it)
    }

    fun back() {
        viewModelScope.launch {
            navigationFlow.navigate(Router::back)
        }
    }

    fun openDetailsScreen(movieId: Int) {
        viewModelScope.launch {
            navigationFlow.navigate {
                fromHomeToDetails(movieId)
            }
        }
    }
}
