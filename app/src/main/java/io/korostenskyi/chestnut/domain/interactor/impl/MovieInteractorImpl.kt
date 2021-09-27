package io.korostenskyi.chestnut.domain.interactor.impl

import io.korostenskyi.chestnut.domain.interactor.MovieInteractor
import io.korostenskyi.chestnut.domain.model.Movie
import io.korostenskyi.chestnut.domain.model.MovieInfo
import io.korostenskyi.chestnut.domain.repository.MovieRepository
import javax.inject.Inject

class MovieInteractorImpl @Inject constructor(
    private val repository: MovieRepository
) : MovieInteractor {

    override suspend fun retrievePopularMovies(page: Int): List<Movie> {
        return repository.retrievePopularMovies(page)
    }

    override suspend fun retrieveMovieInfo(id: Int): MovieInfo {
        return repository.retrieveMovieInfo(id)
    }
}
