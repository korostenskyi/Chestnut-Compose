package io.korostenskyi.chestnut.presentation.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import io.korostenskyi.chestnut.R
import io.korostenskyi.chestnut.extensions.items
import io.korostenskyi.chestnut.presentation.composables.MovieCard
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val listState = rememberLazyListState()
    val movies = viewModel.moviesStateFlow.collectAsLazyPagingItems()
    LaunchedEffect(viewModel) {
        launch {
            viewModel.sideEffectFlow.collect {
                handleSideEffect(context, it)
            }
        }
    }
    Column {
        TopAppBar(
            title = {
                Text(stringResource(R.string.app_name))
            }
        )
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 128.dp),
            state = listState
        ) {
            items(movies) { movie ->
                MovieCard(movie!!, onClick = { movie ->
                    println(movie)
                })
            }
        }
    }
}

fun handleSideEffect(context: Context, sideEffect: HomeSideEffect) {
    when (sideEffect) {
        is HomeSideEffect.Toast -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
    }
}
