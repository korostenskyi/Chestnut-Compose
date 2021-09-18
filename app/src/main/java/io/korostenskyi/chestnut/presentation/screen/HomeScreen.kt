package io.korostenskyi.chestnut.presentation.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.korostenskyi.chestnut.presentation.composables.MovieCard
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val context = LocalContext.current
    LaunchedEffect(viewModel) {
        launch {
            viewModel.sideEffectFlow.collect {
                handleSideEffect(context, it)
            }
        }
        launch {
            viewModel.loadPopularMovies()
        }
    }
    val listState = rememberLazyListState()
    val movies = viewModel.moviesStateFlow.collectAsState().value
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 128.dp),
        state = listState
    ) {
        items(movies) { movie ->
            MovieCard(movie, onClick = { movie ->
                println(movie)
            })
        }
    }
}

fun handleSideEffect(context: Context, sideEffect: HomeSideEffect) {
    when (sideEffect) {
        is HomeSideEffect.Toast -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
    }
}
