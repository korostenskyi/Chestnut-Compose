package io.korostenskyi.chestnut.data.repository

import io.korostenskyi.chestnut.data.local.AppDatabase
import io.korostenskyi.chestnut.data.local.model.FavoriteMovieRoom
import io.korostenskyi.chestnut.data.network.MovieNetworkDataSource
import io.korostenskyi.chestnut.data.network.mapper.ApiResponseMapper
import io.korostenskyi.chestnut.domain.model.*
import io.korostenskyi.chestnut.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Locale
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieNetworkDataSource: MovieNetworkDataSource,
    private val mapper: ApiResponseMapper,
    private val database: AppDatabase
) : MovieRepository {

    private val language: String
        get() = Locale.getDefault().toLanguageTag()

    override val favoriteMoviesFlow: Flow<List<Int>> = database.favoriteMoviesDao().getAllFavorites().map {
        it.map { model -> model.movieId }
    }

    override suspend fun retrieveMoviesByIds(ids: List<Int>): List<Movie> {
        return ids.map { movieNetworkDataSource.fetchMovieDetails(it, language).let(mapper::mapToMovie) }
    }

    // TODO: Handle errors
    override suspend fun retrievePopularMovies(page: Int): MoviePage {
        return movieNetworkDataSource.fetchPopularMovies(page, language).let(mapper::map)
    }

    override suspend fun retrieveMovieInfo(id: Int): MovieDetails {
        val info = movieNetworkDataSource.fetchMovieDetails(id, language).let(mapper::map)
        val credits = movieNetworkDataSource.fetchMovieCredits(id, language).let(mapper::map)
        return MovieDetails(info, credits)
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
