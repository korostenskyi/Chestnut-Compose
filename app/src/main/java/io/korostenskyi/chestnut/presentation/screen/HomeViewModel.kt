package io.korostenskyi.chestnut.presentation.screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.korostenskyi.chestnut.data.network.MovieNetworkDataSource
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataSource: MovieNetworkDataSource
) : ContainerHost<HomeState, HomeSideEffect>, ViewModel() {

    override val container = container<HomeState, HomeSideEffect>(HomeState())

    fun add(number: Int) = intent {
        postSideEffect(HomeSideEffect.Toast("Adding $number to ${state.total}!"))
        val movies = dataSource.fetchPopularMovies(1).movies
        reduce {
            state.copy(total = movies.count())
        }
    }
}
