package io.korostenskyi.chestnut.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import io.korostenskyi.chestnut.domain.model.Movie

@Composable
fun MovieCard(movie: Movie) {
     Card {
        Image(
            painter = rememberImagePainter(
                data = movie.posterPath,
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
     }
}
