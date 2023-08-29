package io.korostenskyi.chestnut.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import io.korostenskyi.chestnut.domain.model.ApplicationSettings
import io.korostenskyi.chestnut.presentation.navigation.NavigationFlow
import io.korostenskyi.chestnut.presentation.navigation.NavigationNames
import io.korostenskyi.chestnut.presentation.navigation.Router
import io.korostenskyi.chestnut.presentation.navigation.RouterImpl
import io.korostenskyi.chestnut.presentation.screen.details.DetailsScreen
import io.korostenskyi.chestnut.presentation.screen.details.DetailsViewModel
import io.korostenskyi.chestnut.presentation.screen.favorites.FavoritesScreen
import io.korostenskyi.chestnut.presentation.screen.favorites.FavoritesViewModel
import io.korostenskyi.chestnut.presentation.screen.home.HomeScreen
import io.korostenskyi.chestnut.presentation.screen.home.HomeViewModel
import io.korostenskyi.chestnut.presentation.screen.settings.SettingsScreen
import io.korostenskyi.chestnut.presentation.screen.settings.SettingsViewModel
import io.korostenskyi.chestnut.presentation.theme.ChestnutTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var navigationFlow: NavigationFlow
    @Inject lateinit var detailsFactory: DetailsViewModel.DetailsAssistedFactory

    private val viewModel by viewModels<MainViewModel>()
    private val favoritesViewModel by viewModels<FavoritesViewModel>()
    private val homeViewModel by viewModels<HomeViewModel>()
    private val settingsViewModel by viewModels<SettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val router: Router = RouterImpl(navController)
            val settings = viewModel.settingsFlow.collectAsState().value
            ChestnutApp(settings) {
                LaunchedEffect(Router::class.java) {
                    navigationFlow.collect(router)
                }
                NavHost(navController = navController, startDestination = NavigationNames.Home) {
                    composable(NavigationNames.Home) {
                        HomeScreen(viewModel = homeViewModel)
                    }
                    composable(NavigationNames.Favorites) {
                        FavoritesScreen(viewModel = favoritesViewModel)
                    }
                    composable(
                        route = "${NavigationNames.Details}/{${NavigationNames.MovieIdArgument}}",
                        arguments = listOf(
                            navArgument(NavigationNames.MovieIdArgument) {
                                type = NavType.IntType
                            }
                        )
                    ) { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getInt(NavigationNames.MovieIdArgument)!!
                        val viewModel = viewModel<DetailsViewModel>(
                            factory = DetailsViewModel.provideFactory(detailsFactory, movieId)
                        )
                        DetailsScreen(viewModel)
                    }
                    composable(NavigationNames.Settings) {
                        SettingsScreen(viewModel = settingsViewModel)
                    }
                }
            }
        }
    }
}

@Composable
fun ChestnutApp(
    settings: ApplicationSettings,
    content: @Composable () -> Unit
) {
    ChestnutTheme(settings.theme) {
        Surface(color = MaterialTheme.colorScheme.background) {
            content()
        }
    }
}
