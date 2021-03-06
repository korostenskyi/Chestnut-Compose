package io.korostenskyi.chestnut.presentation.screen.favorites

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.korostenskyi.chestnut.R
import io.korostenskyi.chestnut.presentation.composables.MovieCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel = hiltViewModel()) {
    val listState = rememberLazyListState()
    val movies = viewModel.favoriteMovies.collectAsState(initial = emptyList()).value
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SmallTopAppBar(
            title = {
                Text(text = stringResource(id = R.string.title_favorites))
            },
            navigationIcon = {
                IconButton(onClick = { viewModel.back() }) {
                    Image(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.action_back),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                    )
                }
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
                    movie = movie,
                    onClick = { viewModel.openDetailsScreen(it.id) },
                    modifier = Modifier
                        .padding(1.dp)
                )
            }
        }
    }
}
