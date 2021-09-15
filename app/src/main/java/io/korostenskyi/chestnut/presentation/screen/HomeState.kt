package io.korostenskyi.chestnut.presentation.screen

import io.korostenskyi.chestnut.domain.model.Movie

data class HomeState(
    val movies: List<Movie> = emptyList()
)
