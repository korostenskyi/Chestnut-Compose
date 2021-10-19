package io.korostenskyi.chestnut.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import io.korostenskyi.chestnut.domain.model.Movie

@Composable
fun MovieCard(movie: Movie, onClick: (Movie) -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12f))
            .clickable { onClick(movie) }
    ) {
        Image(
            painter = rememberImagePainter(
                data = movie.posterPath,
                builder = {
                    crossfade(true)
                    scale(Scale.FILL)
                    transformations(RoundedCornersTransformation(12f))
                },
            ),
            contentDescription = null,
            modifier = Modifier
                .height(180.dp)
                .fillMaxSize()
        )
        Surface(
            color = Color(0xCC000000),
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = movie.title,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(2.dp)
            )
        }
    }
}
