package io.korostenskyi.chestnut.presentation.screen.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import io.korostenskyi.chestnut.R
import io.korostenskyi.chestnut.domain.model.MovieDetails
import io.korostenskyi.chestnut.presentation.composables.ActorCard
import io.korostenskyi.chestnut.presentation.composables.ExpandableText
import io.korostenskyi.chestnut.presentation.composables.LoadingView
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(viewModel: DetailsViewModel) {
    val state = viewModel.detailsStateFlow.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TopAppBar(
            title = {
                if (state is DetailsState.Success) {
                    Text(state.details.info.title)
                } else {
                    Text(stringResource(R.string.title_details))
                }
            },
            actions = {
                if (state is DetailsState.Success) {
                    IconButton(onClick = { viewModel.share(state.details.info) }) {
                        Image(
                            imageVector = Icons.Default.Share,
                            contentDescription = stringResource(id = R.string.action_share),
                            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface)
                        )
                    }
                }
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
        when (state) {
            is DetailsState.Idle -> {}
            is DetailsState.Loading -> LoadingView()
            is DetailsState.Success -> DetailsView(details = state.details, viewModel = viewModel)
        }
    }
}

@Composable
fun DetailsView(
    details: MovieDetails,
    viewModel: DetailsViewModel
) {
    val (movie, credits) = details
    val isInFavorites = viewModel.favoritesFlow.collectAsState().value
    if (movie.backdropPath != null) {
        SubcomposeAsyncImage(
            model = movie.backdropPath,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            loading = {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            },
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
                    style = MaterialTheme.typography.headlineSmall,
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
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
                        )
                    } else {
                        Image(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = stringResource(id = R.string.action_remove_from_favorites),
                            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground)
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                movie.releaseDate?.let {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = stringResource(id = R.string.details_release_date),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.size(4.dp))
                        Text(text = it)
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = stringResource(id = R.string.details_average_rating),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = movie.voteAverage.roundToInt().toString())
                }
            }
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.onBackground)
        if (movie.description.isNotBlank()) {
            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.details_description),
                    style = MaterialTheme.typography.headlineSmall
                )
                ExpandableText(
                    text = movie.description,
                    overflowText = stringResource(id = R.string.label_see_more),
                    minimumLines = 3
                )
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.onBackground)
        }
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.details_cast),
                style = MaterialTheme.typography.headlineSmall
            )
            LazyRow {
                items(credits.cast) { actor ->
                    ActorCard(actor = actor)
                }
            }
        }
    }
}
