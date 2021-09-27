package io.korostenskyi.chestnut.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.korostenskyi.chestnut.domain.interactor.MovieInteractor
import io.korostenskyi.chestnut.presentation.navigation.NavigationNames
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel @AssistedInject constructor(
    private val movieInteractor: MovieInteractor,
    @Assisted(NavigationNames.MovieIdArgument) private val movieId: Int
) : ViewModel() {

    private val _detailsStateFlow = MutableStateFlow<DetailsState>(DetailsState.Idle)
    val detailsStateFlow = _detailsStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            _detailsStateFlow.emit(DetailsState.Loading)
            val movieInfo = movieInteractor.retrieveMovieInfo(movieId)
            _detailsStateFlow.emit(DetailsState.Success(movieInfo))
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
