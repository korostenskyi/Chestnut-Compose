package io.korostenskyi.chestnut.domain.repository

import io.korostenskyi.chestnut.domain.model.Movie
import io.korostenskyi.chestnut.domain.model.MovieInfo

interface MovieRepository {

    suspend fun retrievePopularMovies(page: Int): List<Movie>

    suspend fun retrieveMovieInfo(id: Int): MovieInfo
}
