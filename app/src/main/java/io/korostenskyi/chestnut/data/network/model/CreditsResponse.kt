package io.korostenskyi.chestnut.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreditsResponse(
    @SerialName("cast") val cast: List<PersonResponse>,
    @SerialName("crew") val crew: List<PersonResponse>
) {

    @Serializable
    data class PersonResponse(
        @SerialName("name") val name: String,
        @SerialName("profile_path") val photoPath: String? = null,
        @SerialName("character") val character: String? = null
    )
}
