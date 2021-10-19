package io.korostenskyi.chestnut.domain.model

data class BuildParams(
    val tmdbBaseUrl: String,
    val tmdbApiKey: String,
    val isDebug: Boolean,
    val isSentryEnabled: Boolean,
    val sentryUrl: String
)
