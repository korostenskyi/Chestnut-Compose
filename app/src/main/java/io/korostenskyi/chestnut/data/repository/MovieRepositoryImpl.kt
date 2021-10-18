package io.korostenskyi.chestnut.data.repository

import io.korostenskyi.chestnut.data.local.AppDatabase
import io.korostenskyi.chestnut.data.local.model.FavoriteMovieRoom
import io.korostenskyi.chestnut.data.network.MovieNetworkDataSource
import io.korostenskyi.chestnut.data.network.mapper.ApiResponseMapper
import io.korostenskyi.chestnut.domain.model.Movie
import io.korostenskyi.chestnut.domain.model.MovieInfo
import io.korostenskyi.chestnut.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieNetworkDataSource: MovieNetworkDataSource,
    private val mapper: ApiResponseMapper,
    private val database: AppDatabase
) : MovieRepository {

    override val favoriteMoviesFlow: Flow<List<Int>> = database.favoriteMoviesDao().getAllFavorites().map {
        it.map { model -> model.movieId }
    }

    // TODO: Handle errors
    override suspend fun retrievePopularMovies(page: Int): List<Movie> {
        return movieNetworkDataSource.fetchPopularMovies(page).movies.map(mapper::map)
    }

    override suspend fun retrieveMovieInfo(id: Int): MovieInfo {
        return movieNetworkDataSource.fetchMovieDetails(id).let(mapper::map)
    }

    override suspend fun addToFavorites(id: Int) {
        val model = FavoriteMovieRoom(movieId = id)
        database.favoriteMoviesDao().addToFavorites(model)
    }

    override suspend fun removeFromFavorites(id: Int) {
        val model = FavoriteMovieRoom(movieId = id)
        database.favoriteMoviesDao().removeFromFavorites(model)
    }
}
