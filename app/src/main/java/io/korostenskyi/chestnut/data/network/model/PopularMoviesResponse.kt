package io.korostenskyi.chestnut.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PopularMoviesResponse(
    @SerialName("page") val page: Int,
    @SerialName("total_results") val totalResults: Int,
    @SerialName("total_pages") val totalPages: Int,
    @SerialName("results") val movies: List<MovieResponse>
)