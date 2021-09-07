package io.korostenskyi.chestnut.presentation.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state = viewModel.container.stateFlow.collectAsState().value
    val context = LocalContext.current
    LaunchedEffect(viewModel) {
        launch {
            viewModel.add(123)
            viewModel.container.sideEffectFlow.collect {
                handleSideEffect(context, it)
            }
        }
    }
    Column(Modifier.padding(all = 2.dp)) {
        Button(onClick = { viewModel.add(1) }) {
            Text(text = "Click me")
        }
        Message(text = state.total.toString())
    }
}

@Composable
fun Message(text: String) {
    Text(text = text)
}

fun handleSideEffect(context: Context, sideEffect: HomeSideEffect) {
    when (sideEffect) {
        is HomeSideEffect.Toast -> Toast.makeText(context, sideEffect.message, Toast.LENGTH_SHORT).show()
    }
}
