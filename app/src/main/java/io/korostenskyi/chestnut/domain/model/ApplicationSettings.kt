package io.korostenskyi.chestnut.domain.model

data class ApplicationSettings(
    val theme: Theme = Theme.SYSTEM
) {

    enum class Theme {
        SYSTEM,
        LIGHT,
        DARK
    }
}
