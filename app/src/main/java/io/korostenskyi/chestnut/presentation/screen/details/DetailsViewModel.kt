package io.korostenskyi.chestnut.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.korostenskyi.chestnut.presentation.navigation.NavigationNames

class DetailsViewModel @AssistedInject constructor(
    @Assisted(NavigationNames.MovieIdArgument) private val movieId: Long
) : ViewModel() {

    @AssistedFactory
    interface DetailsAssistedFactory {

        fun create(@Assisted(NavigationNames.MovieIdArgument) movieId: Long): DetailsViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: DetailsAssistedFactory,
            movieId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(movieId) as T
            }
        }
    }
}
