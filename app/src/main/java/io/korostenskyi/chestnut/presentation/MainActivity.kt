package io.korostenskyi.chestnut.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import io.korostenskyi.chestnut.presentation.navigation.NavigationFlow
import io.korostenskyi.chestnut.presentation.navigation.NavigationNames
import io.korostenskyi.chestnut.presentation.navigation.Router
import io.korostenskyi.chestnut.presentation.navigation.RouterImpl
import io.korostenskyi.chestnut.presentation.screen.details.DetailsScreen
import io.korostenskyi.chestnut.presentation.screen.details.DetailsViewModel
import io.korostenskyi.chestnut.presentation.screen.home.HomeScreen
import io.korostenskyi.chestnut.presentation.theme.ChestnutTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var navigationFlow: NavigationFlow
    @Inject lateinit var detailsFactory: DetailsViewModel.DetailsAssistedFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val router: Router = RouterImpl(navController)
            ChestnutApp {
                LaunchedEffect(Router::class.java) {
                    navigationFlow.collect(router)
                }
                NavHost(navController = navController, startDestination = NavigationNames.Home) {
                    composable(NavigationNames.Home) {
                        HomeScreen()
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
                }
            }
        }
    }
}

@Composable
fun ChestnutApp(content: @Composable () -> Unit) {
    ChestnutTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}
