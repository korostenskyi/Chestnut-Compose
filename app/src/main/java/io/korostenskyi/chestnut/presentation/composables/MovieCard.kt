package io.korostenskyi.chestnut.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import io.korostenskyi.chestnut.domain.model.Movie

@Composable
fun MovieCard(movie: Movie, onClick: (Movie) -> Unit) {
     Card(modifier = Modifier.clickable { onClick(movie) }) {
        Image(
            painter = rememberImagePainter(
                data = movie.posterPath,
                builder = {
                    crossfade(true)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .height(180.dp)
                .width(120.dp)
        )
     }
}
