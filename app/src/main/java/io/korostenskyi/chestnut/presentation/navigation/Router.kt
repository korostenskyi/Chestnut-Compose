package io.korostenskyi.chestnut.presentation.navigation

interface Router {

    fun back()

    fun navigate(navigator: (Router) -> Unit)

    fun fromPopularToDetails(movieId: Long)
}
