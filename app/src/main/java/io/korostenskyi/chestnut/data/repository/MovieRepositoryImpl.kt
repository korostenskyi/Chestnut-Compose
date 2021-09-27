package io.korostenskyi.chestnut.data.repository

import io.korostenskyi.chestnut.data.network.MovieNetworkDataSource
import io.korostenskyi.chestnut.data.network.mapper.ApiResponseMapper
import io.korostenskyi.chestnut.domain.model.Movie
import io.korostenskyi.chestnut.domain.model.MovieInfo
import io.korostenskyi.chestnut.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieNetworkDataSource: MovieNetworkDataSource,
    private val mapper: ApiResponseMapper
) : MovieRepository {

    // TODO: Handle errors
    override suspend fun retrievePopularMovies(page: Int): List<Movie> {
        return movieNetworkDataSource.fetchPopularMovies(page).movies.map(mapper::map)
    }

    override suspend fun retrieveMovieInfo(id: Int): MovieInfo {
        return movieNetworkDataSource.fetchMovieDetails(id).let(mapper::map)
    }
}
