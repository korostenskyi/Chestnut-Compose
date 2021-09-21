package io.korostenskyi.chestnut.presentation.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import io.korostenskyi.chestnut.R
import io.korostenskyi.chestnut.extensions.items
import io.korostenskyi.chestnut.presentation.composables.MovieCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val listState = rememberLazyListState()
    val movies = viewModel.moviesStateFlow.collectAsLazyPagingItems()
    Column {
        TopAppBar(
            title = {
                Text(stringResource(R.string.title_popular))
            }
        )
        LazyVerticalGrid(
            cells = GridCells.Adaptive(minSize = 128.dp),
            state = listState
        ) {
            items(movies) { movie ->
                MovieCard(movie!!, onClick = { movie ->
                    viewModel.openDetailsScreen(movie.id)
                })
            }
        }
    }
}
