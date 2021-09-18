package io.korostenskyi.chestnut.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.korostenskyi.chestnut.domain.interactor.MovieInteractor
import io.korostenskyi.chestnut.domain.model.Movie
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieInteractor: MovieInteractor
) : ViewModel() {

    private val _sideEffectFlow = MutableSharedFlow<HomeSideEffect>()
    val sideEffectFlow = _sideEffectFlow.asSharedFlow()

    private val _moviesStateFlow = MutableStateFlow<List<Movie>>(emptyList())
    val moviesStateFlow = _moviesStateFlow.asStateFlow()

    fun loadPopularMovies(page: Int = 1) {
        viewModelScope.launch {
            val newData = movieInteractor.retrievePopularMovies(page)
            val movies = moviesStateFlow.value + newData
            _moviesStateFlow.emit(movies)
            _sideEffectFlow.emit(HomeSideEffect.Toast("Loaded ${movies.count()} movies!"))
        }
    }
}
