package io.korostenskyi.chestnut.presentation.screen.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import coil.size.Scale
import io.korostenskyi.chestnut.R
import io.korostenskyi.chestnut.domain.model.MovieInfo
import io.korostenskyi.chestnut.presentation.composables.ExpandableText

@Composable
fun DetailsScreen(viewModel: DetailsViewModel) {
    val state = viewModel.detailsStateFlow.collectAsState().value
    Column {
        TopAppBar(
            title = {
                if (state is DetailsState.Success) {
                    Text(state.movieInfo.title)
                } else {
                    Text(stringResource(R.string.title_details))
                }
            },
            actions = {
                if (state is DetailsState.Success) {
                    IconButton(onClick = { viewModel.share(state.movieInfo) }) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = null
                        )
                    }
                }
            },
            navigationIcon = {
                IconButton(onClick = { viewModel.back() }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = null
                    )
                }
            }
        )
        when (state) {
            is DetailsState.Idle -> {}
            is DetailsState.Loading -> LoadingView()
            is DetailsState.Success -> DetailsView(state.movieInfo, viewModel)
        }
    }
}

@Composable
fun LoadingView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        Text(stringResource(R.string.state_loading))
    }
}

@Composable
fun DetailsView(
    movie: MovieInfo,
    viewModel: DetailsViewModel
) {
    val isInFavorites = viewModel.isInFavoritesFlow.collectAsState().value
    if (movie.backdropPath != null) {
        Image(
            painter = rememberImagePainter(
                data = movie.backdropPath,
                builder = {
                    crossfade(true)
                    scale(Scale.FIT)
                    size(OriginalSize)
                }
            ),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            Row {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier
                        .weight(12f)
                )
                IconButton(
                    onClick = { viewModel.toggleFavorites(movie) },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    if (isInFavorites) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_favorite_filled),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(
                                color = Color.Black
                            )
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.ic_favorite_outlined),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(
                                color = Color.Black
                            )
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_time), contentDescription = null)
                    Text(text = movie.releaseDate)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_star), contentDescription = null)
                    Text(text = movie.voteAverage.toString())
                }
            }
        }
        Divider()
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.details_description),
                style = MaterialTheme.typography.h5
            )
            ExpandableText(
                text = movie.description,
                overflowText = stringResource(id = R.string.label_see_more),
                minimumLines = 3
            )
        }
        Divider()
    }
}
