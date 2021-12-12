package io.korostenskyi.chestnut.presentation.screen.details

import io.korostenskyi.chestnut.domain.model.MovieDetails

sealed class DetailsState {

    object Idle : DetailsState()

    object Loading : DetailsState()

    data class Success(val details: MovieDetails) : DetailsState()
}
