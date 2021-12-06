package io.korostenskyi.chestnut.domain.model

data class MoviePage(
    val page: Int,
    val totalPages: Int,
    val movies: List<Movie>
)
