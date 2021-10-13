package io.korostenskyi.chestnut.presentation.utils

import android.content.Context
import android.content.Intent

fun share(context: Context, text: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, text)
    }
    context.startActivity(sendIntent)
}
