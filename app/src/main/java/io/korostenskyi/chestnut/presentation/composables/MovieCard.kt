package io.korostenskyi.chestnut.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import io.korostenskyi.chestnut.domain.model.Movie

@Composable
fun MovieCard(movie: Movie, onClick: (Movie) -> Unit) {
    Image(
        painter = rememberImagePainter(
            data = movie.posterPath,
            builder = {
                crossfade(true)
                scale(Scale.FILL)
                transformations(RoundedCornersTransformation(12f))
            }
        ),
        contentDescription = null,
        modifier = Modifier
            .height(180.dp)
            .width(120.dp)
            .padding(2.dp)
            .clickable { onClick(movie) }
    )
}
