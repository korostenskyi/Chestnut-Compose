package io.korostenskyi.chestnut.data.network.mapper

import android.content.Context
import android.text.format.DateFormat
import dagger.hilt.android.qualifiers.ApplicationContext
import io.korostenskyi.chestnut.data.network.model.CreditsResponse
import io.korostenskyi.chestnut.data.network.model.MovieInfoResponse
import io.korostenskyi.chestnut.data.network.model.MovieResponse
import io.korostenskyi.chestnut.data.network.model.PopularMoviesResponse
import io.korostenskyi.chestnut.domain.model.Credits
import io.korostenskyi.chestnut.domain.model.Movie
import io.korostenskyi.chestnut.domain.model.MovieInfo
import io.korostenskyi.chestnut.domain.model.MoviePage
import java.text.SimpleDateFormat
import javax.inject.Inject

class ApiResponseMapper @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val dateFormat = DateFormat.getLongDateFormat(context)

    fun map(response: MovieResponse): Movie {
        return Movie(
            id = response.id,
            title = response.title,
            description = response.description,
            posterPath = response.posterPath?.let { "$POSTER_BASE_URL$it" },
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
            posterPath = response.posterPath?.let { "$POSTER_BASE_URL$it" },
            backdropPath = response.backdropPath?.let { "$BACKDROP_BASE_URL$it" },
            isAdult = response.isAdult,
            voteAverage = response.voteAverage,
            voteCount = response.voteCount,
            releaseDate = response.releaseDate?.let { formatDateString(it) }
        )
    }

    fun map(response: PopularMoviesResponse): MoviePage {
        return MoviePage(
            page = response.page,
            totalPages = response.totalPages,
            movies = response.movies.map(::map)
        )
    }

    fun mapToMovie(response: MovieInfoResponse): Movie {
        return Movie(
            id = response.id,
            title = response.title,
            description = response.description,
            posterPath = response.posterPath?.let { "$POSTER_BASE_URL$it" },
            backdropPath = response.backdropPath?.let { "$BACKDROP_BASE_URL$it" },
            isAdult = response.isAdult,
            voteAverage = response.voteAverage,
            voteCount = response.voteCount
        )
    }

    fun map(response: CreditsResponse): Credits {
        return Credits(
            cast = response.cast.map(::map),
            crew = response.crew.map(::map)
        )
    }

    fun map(response: CreditsResponse.PersonResponse): Credits.Person {
        return Credits.Person(
            name = response.name,
            photoPath = response.photoPath?.let { "$PROFILE_PICTURE_BASE_URL$it" },
            character = response.character
        )
    }

    private fun formatDateString(date: String): String {
        val date = inputFormatter.parse(date)
        return dateFormat.format(date)
    }

    companion object {
        private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p"
        private const val POSTER_BASE_URL = "$IMAGE_BASE_URL/w185_and_h278_bestv2"
        private const val BACKDROP_BASE_URL = "$IMAGE_BASE_URL/w1280"
        private const val PROFILE_PICTURE_BASE_URL = "$IMAGE_BASE_URL/h632"
        private const val INPUT_DATE_FORMAT = "yyyy-MM-dd"
        private val inputFormatter = SimpleDateFormat(INPUT_DATE_FORMAT)
    }
}
