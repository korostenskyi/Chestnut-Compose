package io.korostenskyi.chestnut.domain.interactor

import io.korostenskyi.chestnut.domain.model.Movie
import io.korostenskyi.chestnut.domain.model.MovieInfo
import kotlinx.coroutines.flow.Flow

interface MovieInteractor {

    val favoriteMoviesFlow: Flow<List<Int>>

    suspend fun retrievePopularMovies(page: Int): List<Movie>

    suspend fun retrieveMovieInfo(id: Int): MovieInfo

    suspend fun addToFavorites(id: Int)

    suspend fun removeFromFavorites(id: Int)
}
