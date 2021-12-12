package io.korostenskyi.chestnut.presentation.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val listState = rememberLazyListState()
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
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
                    )
                }
                IconButton(onClick = { viewModel.openSettingsScreen() }) {
                    Image(
                        imageVector = Icons.Default.Settings,
                        contentDescription = stringResource(id = R.string.title_settings),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
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
                    backgroundColor = MaterialTheme.colors.primary,
                    shape = CircleShape,
                )
            }
        ) {
            LazyVerticalGrid(
                cells = GridCells.Adaptive(minSize = 128.dp),
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
                    }
                }
            }
        }
    }
}
