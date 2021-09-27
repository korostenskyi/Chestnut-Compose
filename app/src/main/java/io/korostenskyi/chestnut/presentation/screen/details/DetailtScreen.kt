package io.korostenskyi.chestnut.presentation.screen.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import io.korostenskyi.chestnut.R

@Composable
fun DetailsScreen(viewModel: DetailsViewModel) {
    val movieInfo = viewModel.movieDetailsFlow.collectAsState().value
    Column {
        TopAppBar(
            title = {
                Text(stringResource(R.string.title_details))
            }
        )
        if (movieInfo != null) {
            Text(text = movieInfo.title)
        } else {
            Column {
                CircularProgressIndicator()
                Text(text = stringResource(R.string.state_loading))
            }
        }
    }
}
