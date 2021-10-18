package io.korostenskyi.chestnut.data.network.mapper

import io.korostenskyi.chestnut.data.network.model.MovieInfoResponse
import io.korostenskyi.chestnut.data.network.model.MovieResponse
import io.korostenskyi.chestnut.domain.model.Movie
import io.korostenskyi.chestnut.domain.model.MovieInfo
import javax.inject.Inject

class ApiResponseMapper @Inject constructor() {

    fun map(response: MovieResponse): Movie {
        return Movie(
            id = response.id,
            title = response.title,
            description = response.description,
            posterPath = "$POSTER_BASE_URL${response.posterPath}",
            backdropPath = response.backdropPath?.let { "$BACKDROP_BASE_URL$it" },
            isAdult = response.isAdult,
            voteAverage = response.voteAverage,
            voteCount = response.voteCount
        )
    }

    fun map(response: MovieInfoResponse): MovieInfo {
        return MovieInfo(
            id = response.id,
            title = response.title,
            description = response.description,
            posterPath = "$POSTER_BASE_URL${response.posterPath}",
            backdropPath = response.backdropPath?.let { "$BACKDROP_BASE_URL$it" },
            isAdult = response.isAdult,
            voteAverage = response.voteAverage,
            voteCount = response.voteCount,
            releaseDate = response.releaseDate
        )
    }

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p"
        private const val POSTER_BASE_URL = "$IMAGE_BASE_URL/w185_and_h278_bestv2"
        private const val BACKDROP_BASE_URL = "$IMAGE_BASE_URL/w1280"
    }
}
