package io.korostenskyi.chestnut.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import io.korostenskyi.chestnut.domain.model.Credits

@Composable
fun ActorCard(
    actor: Credits.Person,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .background(color = MaterialTheme.colors.surface)
            .width(100.dp)
            .clip(RoundedCornerShape(12f))
            .padding(horizontal = 4.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (actor.photoPath != null) {
                AsyncImage(
                    model = actor.photoPath,
                    loading = {
                        CircularProgressIndicator()
                    },
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            } else {
                Image(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = actor.name,
                    style = MaterialTheme.typography.body1
                )
                actor.character?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}
