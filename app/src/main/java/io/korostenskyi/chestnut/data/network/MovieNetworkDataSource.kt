package io.korostenskyi.chestnut.data.network

import io.korostenskyi.chestnut.data.network.model.MovieInfoResponse
import io.korostenskyi.chestnut.data.network.model.PopularMoviesResponse

interface MovieNetworkDataSource {

    suspend fun fetchPopularMovies(page: Int): PopularMoviesResponse

    suspend fun fetchMovieDetails(id: Int): MovieInfoResponse
}