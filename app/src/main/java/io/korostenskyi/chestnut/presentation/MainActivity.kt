package io.korostenskyi.chestnut.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import dagger.hilt.android.AndroidEntryPoint
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
