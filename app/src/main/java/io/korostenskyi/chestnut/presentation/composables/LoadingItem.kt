package io.korostenskyi.chestnut.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.korostenskyi.chestnut.R

@Composable
fun LoadingItem() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(180.dp)
            .width(120.dp)
            .padding(2.dp)
            .clip(RoundedCornerShape(12f))
    ) {
        CircularProgressIndicator()
        Text(stringResource(R.string.state_loading))
    }
}