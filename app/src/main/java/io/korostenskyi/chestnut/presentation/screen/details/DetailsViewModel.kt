package io.korostenskyi.chestnut.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.korostenskyi.chestnut.domain.interactor.MovieInteractor
import io.korostenskyi.chestnut.domain.model.MovieInfo
import io.korostenskyi.chestnut.presentation.navigation.NavigationNames
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel @AssistedInject constructor(
    private val movieInteractor: MovieInteractor,
    @Assisted(NavigationNames.MovieIdArgument) private val movieId: Int
) : ViewModel() {

    private val _movieDetailsFlow = MutableStateFlow<MovieInfo?>(null)
    val movieDetailsFlow = _movieDetailsFlow.asStateFlow()

    init {
        viewModelScope.launch {
            val movieInfo = movieInteractor.retrieveMovieInfo(movieId)
            delay(500)
            _movieDetailsFlow.emit(movieInfo)
        }
    }

    @AssistedFactory
    interface DetailsAssistedFactory {

        fun create(@Assisted(NavigationNames.MovieIdArgument) movieId: Int): DetailsViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: DetailsAssistedFactory,
            movieId: Int
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(movieId) as T
            }
        }
    }
}
