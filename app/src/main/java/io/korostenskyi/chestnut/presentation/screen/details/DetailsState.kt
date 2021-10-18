package io.korostenskyi.chestnut.presentation.screen.details

import io.korostenskyi.chestnut.domain.model.MovieInfo

sealed class DetailsState {

    object Idle : DetailsState()

    object Loading : DetailsState()

    data class Success(val movieInfo: MovieInfo) : DetailsState()
}
