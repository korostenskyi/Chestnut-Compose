package io.korostenskyi.chestnut.presentation.navigation

interface Router {

    fun back()

    fun navigate(navigator: (Router) -> Unit)

    fun fromHomeToDetails(movieId: Int)

    fun fromHomeToSettings()

    fun fromHomeToFavorites()
}
