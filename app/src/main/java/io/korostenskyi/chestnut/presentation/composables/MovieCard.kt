package io.korostenskyi.chestnut.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import io.korostenskyi.chestnut.domain.model.Movie

@Composable
fun MovieCard(
    movie: Movie,
    onClick: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12f))
            .clickable { onClick(movie) }
    ) {
        SubcomposeAsyncImage(
            model = movie.posterPath,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            loading = {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary
                )
            },
            modifier = Modifier
                .height(180.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(12f))
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
