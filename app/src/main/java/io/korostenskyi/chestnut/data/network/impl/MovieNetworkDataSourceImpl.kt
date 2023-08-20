package io.korostenskyi.chestnut.data.network.impl

import io.korostenskyi.chestnut.BuildConfig
import io.korostenskyi.chestnut.data.network.MovieNetworkDataSource
import io.korostenskyi.chestnut.data.network.api.TmdbApi
import io.korostenskyi.chestnut.data.network.model.CreditsResponse
import io.korostenskyi.chestnut.data.network.model.MovieInfoResponse
import io.korostenskyi.chestnut.data.network.model.PopularMoviesResponse
import javax.inject.Inject

class MovieNetworkDataSourceImpl @Inject constructor(
    private val api: TmdbApi
) : MovieNetworkDataSource {

    override suspend fun fetchPopularMovies(page: Int, language: String): PopularMoviesResponse {
        return api.fetchPopularMovies(BuildConfig.TMDB_API_KEY, page, language)
    }

    override suspend fun fetchMovieDetails(id: Int, language: String): MovieInfoResponse {
        return api.fetchMovieInfo(id, BuildConfig.TMDB_API_KEY, language)
    }

    override suspend fun fetchMovieCredits(id: Int, language: String): CreditsResponse {
        return api.fetchCredits(id, BuildConfig.TMDB_API_KEY, language)
    }
}
