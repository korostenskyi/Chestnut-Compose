package io.korostenskyi.chestnut.domain.interactor.impl

import io.korostenskyi.chestnut.domain.interactor.MovieInteractor
import io.korostenskyi.chestnut.domain.model.Movie
import io.korostenskyi.chestnut.domain.model.MovieInfo
import io.korostenskyi.chestnut.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractorImpl @Inject constructor(
    private val repository: MovieRepository
) : MovieInteractor {

    override val favoriteMoviesFlow: Flow<List<Int>> = repository.favoriteMoviesFlow

    override suspend fun retrievePopularMovies(page: Int): List<Movie> {
        return repository.retrievePopularMovies(page)
    }

    override suspend fun retrieveMovieInfo(id: Int): MovieInfo {
        return repository.retrieveMovieInfo(id)
    }

    override suspend fun addToFavorites(id: Int) {
        repository.addToFavorites(id)
    }

    override suspend fun removeFromFavorites(id: Int) {
        repository.removeFromFavorites(id)
    }
}
