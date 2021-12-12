package io.korostenskyi.chestnut.domain.interactor

import io.korostenskyi.chestnut.domain.model.Movie
import io.korostenskyi.chestnut.domain.model.MovieDetails
import io.korostenskyi.chestnut.domain.model.MoviePage
import kotlinx.coroutines.flow.Flow

interface MovieInteractor {

    val favoriteMoviesFlow: Flow<List<Int>>

    suspend fun retrievePopularMovies(page: Int): MoviePage

    suspend fun retrieveMovieInfo(id: Int): MovieDetails

    suspend fun retrieveMoviesByIds(ids: List<Int>): List<Movie>

    suspend fun addToFavorites(id: Int)

    suspend fun removeFromFavorites(id: Int)
}
