package io.korostenskyi.chestnut.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun ExpandableText(
    text: String,
    overflowText: String,
    modifier: Modifier = Modifier,
    minimumLines: Int = 1
) {
    var expanded by remember { mutableStateOf(false) }
    var hasVisualOverflow by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
    ) {
        Text(
            text = text,
            maxLines = if (!expanded) minimumLines else Int.MAX_VALUE,
            overflow = if (!expanded) TextOverflow.Clip else TextOverflow.Visible,
            onTextLayout = { result -> hasVisualOverflow = result.hasVisualOverflow }
        )
        if (!expanded && hasVisualOverflow) {
            ClickableText(
                text = AnnotatedString(text = overflowText),
                onClick = { expanded = !expanded },
                style = TextStyle(fontWeight = FontWeight.Bold)
            )
        }
    }
}

