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

    override fun fromHomeToDetails(movieId: Int) {
        navController.navigate("${NavigationNames.Details}/$movieId")
    }

    override fun fromHomeToSettings() {
        navController.navigate(NavigationNames.Settings)
    }

    override fun fromHomeToFavorites() {
        navController.navigate(NavigationNames.Favorites)
    }
}
