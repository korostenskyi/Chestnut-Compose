package io.korostenskyi.chestnut.presentation.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import io.korostenskyi.chestnut.R
import io.korostenskyi.chestnut.extensions.items
import io.korostenskyi.chestnut.presentation.composables.ErrorItem
import io.korostenskyi.chestnut.presentation.composables.LoadingItem
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
            state = listState,
            modifier = Modifier
                .padding(horizontal = 2.dp)
        ) {
            items(movies) { movie ->
                MovieCard(
                    movie = movie!!,
                    onClick = { viewModel.openDetailsScreen(it.id) },
                    modifier = Modifier
                        .padding(1.dp)
                )
            }
            movies.apply {
                when (loadState.append) {
                    is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    is LoadState.Error -> {
                        item { ErrorItem(onRetryClick = { retry() }) }
                    }
                }
            }
        }
    }
}
