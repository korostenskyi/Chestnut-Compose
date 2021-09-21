package io.korostenskyi.chestnut.presentation.screen.details

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.korostenskyi.chestnut.R

@Composable
fun DetailsScreen(viewModel: DetailsViewModel) {
    Column {
        TopAppBar(
            title = {
                Text(stringResource(R.string.title_details))
            }
        )
        Text(text = "Content")
    }
}
