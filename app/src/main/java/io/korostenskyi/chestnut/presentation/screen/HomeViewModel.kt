package io.korostenskyi.chestnut.presentation.screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.korostenskyi.chestnut.domain.interactor.MovieInteractor
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieInteractor: MovieInteractor
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {

    override val container = container<HomeState, HomeSideEffect>(HomeState())

    fun loadPopularMovies(page: Int = 1) = intent {
        val movies = movieInteractor.retrievePopularMovies(page)
        postSideEffect(HomeSideEffect.Toast("Loaded ${movies.count()} movies!"))
        reduce {
            state.copy(movies = movies)
        }
    }
}
