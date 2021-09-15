package io.korostenskyi.chestnut.presentation.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import io.korostenskyi.chestnut.presentation.view.MovieCard
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state = viewModel.container.stateFlow.collectAsState().value
    val context = LocalContext.current
    LaunchedEffect(viewModel) {
        launch {
            viewModel.container.sideEffectFlow.collect {
                handleSideEffect(context, it)
            }
        }
    }
    viewModel.loadPopularMovies()
    LazyColumn {
        items(state.movies) { movie ->
            MovieCard(movie)
        }
    }
}

fun handleSideEffect(context: Context, sideEffect: HomeSideEffect) {
    when (sideEffect) {
        is HomeSideEffect.Toast -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
    }
}
