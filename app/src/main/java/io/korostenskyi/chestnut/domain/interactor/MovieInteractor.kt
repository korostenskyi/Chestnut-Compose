package io.korostenskyi.chestnut.domain.interactor

import io.korostenskyi.chestnut.domain.model.Movie
import io.korostenskyi.chestnut.domain.model.MovieInfo

interface MovieInteractor {

    suspend fun retrievePopularMovies(page: Int): List<Movie>

    suspend fun retrieveMovieInfo(id: Int): MovieInfo
}
