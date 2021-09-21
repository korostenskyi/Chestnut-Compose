package io.korostenskyi.chestnut.presentation.navigation

import androidx.navigation.NavController

class RouterImpl(
    private val navController: NavController
) : Router {

    override fun back() {
        navController.popBackStack()
    }

    override fun navigate(navigator: (Router) -> Unit) {
        navigator(this)
    }

    override fun fromPopularToDetails(movieId: Long) {
        navController.navigate("${NavigationNames.Details}/$movieId")
    }
}
