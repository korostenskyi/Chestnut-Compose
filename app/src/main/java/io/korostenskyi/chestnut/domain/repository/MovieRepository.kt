package io.korostenskyi.chestnut.domain.repository

import io.korostenskyi.chestnut.domain.model.Movie
import io.korostenskyi.chestnut.domain.model.MovieInfo
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    val favoriteMoviesFlow: Flow<List<Int>>

    suspend fun retrievePopularMovies(page: Int): List<Movie>

    suspend fun retrieveMovieInfo(id: Int): MovieInfo

    suspend fun addToFavorites(id: Int)

    suspend fun removeFromFavorites(id: Int)
}
