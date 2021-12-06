package io.korostenskyi.chestnut.domain.repository

import io.korostenskyi.chestnut.domain.model.Movie
import io.korostenskyi.chestnut.domain.model.MovieInfo
import io.korostenskyi.chestnut.domain.model.MoviePage
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    val favoriteMoviesFlow: Flow<List<Int>>

    suspend fun retrievePopularMovies(page: Int): MoviePage

    suspend fun retrieveMovieInfo(id: Int): MovieInfo

    suspend fun retrieveMoviesByIds(ids: List<Int>): List<Movie>

    suspend fun addToFavorites(id: Int)

    suspend fun removeFromFavorites(id: Int)
}
