package io.korostenskyi.chestnut.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import io.korostenskyi.chestnut.R
import io.korostenskyi.chestnut.extensions.items
import io.korostenskyi.chestnut.presentation.composables.ErrorItem
import io.korostenskyi.chestnut.presentation.composables.LoadingItem
import io.korostenskyi.chestnut.presentation.composables.MovieCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val listState = rememberLazyGridState()
    val movies = viewModel.moviesStateFlow.collectAsLazyPagingItems()
    val isRefreshing by viewModel.isRefreshing.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(stringResource(R.string.title_popular))
            },
            actions = {
                IconButton(onClick = { viewModel.openFavoritesScreen() }) {
                    Image(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = stringResource(id = R.string.title_favorites),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                    )
                }
                IconButton(onClick = { viewModel.openSettingsScreen() }) {
                    Image(
                        imageVector = Icons.Default.Settings,
                        contentDescription = stringResource(id = R.string.title_settings),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                    )
                }
            }
        )
        SwipeRefresh(
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = { movies.refresh() },
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    scale = true,
                    backgroundColor = MaterialTheme.colorScheme.primary,
                    shape = CircleShape,
                )
            }
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 128.dp),
                state = listState,
                modifier = Modifier
                    .padding(horizontal = 2.dp)
            ) {
                items(movies) { movie ->
                    movie?.let {
                        MovieCard(
                            movie = it,
                            onClick = { viewModel.openDetailsScreen(it.id) },
                            modifier = Modifier
                                .padding(1.dp)
                        )
                    }
                }
                movies.apply {
                    when (loadState.append) {
                        is LoadState.Loading -> {
                            item { LoadingItem() }
                        }
                        is LoadState.Error -> {
                            item { ErrorItem(onRetryClick = { retry() }) }
                        }
                        is LoadState.NotLoading -> {
                            item { ErrorItem(onRetryClick = { retry() }) }
                        }
                    }
                }
            }
        }
    }
}
