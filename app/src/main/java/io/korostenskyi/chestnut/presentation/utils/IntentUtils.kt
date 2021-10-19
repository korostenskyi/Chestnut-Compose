package io.korostenskyi.chestnut.presentation.utils

import android.content.Context
import android.content.Intent

class IntentUtils(
    private val context: Context
) {

    fun share(text: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra(Intent.EXTRA_TEXT, text)
        }
        context.startActivity(sendIntent)
    }
}
