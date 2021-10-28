package io.korostenskyi.chestnut.data.network.impl

import io.korostenskyi.chestnut.data.network.MovieNetworkDataSource
import io.korostenskyi.chestnut.data.network.api.TmdbApi
import io.korostenskyi.chestnut.data.network.model.MovieInfoResponse
import io.korostenskyi.chestnut.data.network.model.PopularMoviesResponse
import io.korostenskyi.chestnut.domain.model.BuildParams
import javax.inject.Inject

class MovieNetworkDataSourceImpl @Inject constructor(
    private val api: TmdbApi,
    private val buildParams: BuildParams
) : MovieNetworkDataSource {

    override suspend fun fetchPopularMovies(page: Int, language: String): PopularMoviesResponse {
        return api.fetchPopularMovies(buildParams.tmdbApiKey, page, language)
    }

    override suspend fun fetchMovieDetails(id: Int, language: String): MovieInfoResponse {
        return api.fetchMovieInfo(id, buildParams.tmdbApiKey, language)
    }
}
