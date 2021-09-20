package io.korostenskyi.chestnut.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dagger.hilt.android.AndroidEntryPoint
import io.korostenskyi.chestnut.R
import io.korostenskyi.chestnut.presentation.screen.HomeScreen
import io.korostenskyi.chestnut.presentation.theme.ChestnutTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChestnutApp {
                HomeScreen()
            }
        }
    }
}

@Composable
fun ChestnutApp(content: @Composable () -> Unit) {
    ChestnutTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}
