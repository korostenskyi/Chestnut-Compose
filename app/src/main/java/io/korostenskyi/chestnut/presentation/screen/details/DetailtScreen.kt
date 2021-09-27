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
    val state = viewModel.detailsStateFlow.collectAsState().value
    Column {
        TopAppBar(
            title = {
                Text(stringResource(R.string.title_details))
            }
        )
        when (state) {
            is DetailsState.Idle -> {}
            is DetailsState.Loading -> {
                Column {
                    CircularProgressIndicator()
                    Text(text = stringResource(R.string.state_loading))
                }
            }
            is DetailsState.Success -> {
                Text(text = state.movieInfo.title)
            }
        }
    }
}
