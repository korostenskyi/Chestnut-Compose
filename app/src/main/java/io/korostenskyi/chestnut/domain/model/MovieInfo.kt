package io.korostenskyi.chestnut.domain.model

data class MovieInfo(
    val id: Int,
    val title: String,
    val description: String,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val isAdult: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val releaseDate: String? = null
)
