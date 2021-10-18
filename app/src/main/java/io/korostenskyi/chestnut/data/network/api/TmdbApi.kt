package io.korostenskyi.chestnut.data.network.api

import io.korostenskyi.chestnut.data.network.model.MovieInfoResponse
import io.korostenskyi.chestnut.data.network.model.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/popular")
    suspend fun fetchPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): PopularMoviesResponse

    @GET("movie/{movie_id}")
    suspend fun fetchMovieInfo(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): MovieInfoResponse
}
