package io.korostenskyi.chestnut.presentation.screen.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import coil.size.Scale
import io.korostenskyi.chestnut.R
import io.korostenskyi.chestnut.domain.model.MovieInfo
import io.korostenskyi.chestnut.presentation.composables.ExpandableText
import io.korostenskyi.chestnut.presentation.composables.LoadingView

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
                            imageVector = Icons.Default.Share,
                            contentDescription = stringResource(id = R.string.action_share),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
                        )
                    }
                }
            },
            navigationIcon = {
                IconButton(onClick = { viewModel.back() }) {
                    Image(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.action_back),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
                    )
                }
            }
        )
        when (state) {
            is DetailsState.Idle -> {}
            is DetailsState.Loading -> LoadingView(modifier = Modifier.fillMaxSize())
            is DetailsState.Success -> DetailsView(state.movieInfo, viewModel)
        }
    }
}

@Composable
fun DetailsView(
    movie: MovieInfo,
    viewModel: DetailsViewModel
) {
    val isInFavorites = viewModel.favoritesFlow.collectAsState().value
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
                    onClick = { viewModel.toggleFavorites() },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    if (isInFavorites) {
                        Image(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = stringResource(id = R.string.action_remove_from_favorites),
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground)
                        )
                    } else {
                        Image(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(id = R.string.action_remove_from_favorites),
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onBackground)
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
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = stringResource(id = R.string.details_release_date),
                        tint = MaterialTheme.colors.onBackground
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = movie.releaseDate)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = stringResource(id = R.string.details_average_rating),
                        tint = MaterialTheme.colors.onBackground
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = movie.voteAverage.toString())
                }
            }
        }
        Divider(color = MaterialTheme.colors.onBackground)
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
        Divider(color = MaterialTheme.colors.onBackground)
    }
}
