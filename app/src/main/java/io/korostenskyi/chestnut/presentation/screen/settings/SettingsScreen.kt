package io.korostenskyi.chestnut.presentation.screen.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.korostenskyi.chestnut.R
import io.korostenskyi.chestnut.domain.model.ApplicationSettings

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val settings = viewModel.settingsFlow.collectAsState().value
    var openDialog by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.title_settings))
            },
            navigationIcon = {
                IconButton(onClick = { viewModel.back() }) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = stringResource(id = R.string.action_back)
                    )
                }
            }
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clickable { openDialog = true }
                .padding(horizontal = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.settings_theme))

        }
        Divider()
        if (openDialog) {
            AlertDialog(
                onDismissRequest = { openDialog = false },
                title = {
                    Text(text = stringResource(id = R.string.settings_select_theme))
                },
                text = {
                    Column {
                        enumValues<ApplicationSettings.Theme>().forEach { theme ->
                            val textId = when (theme) {
                                ApplicationSettings.Theme.SYSTEM -> R.string.settings_theme_system
                                ApplicationSettings.Theme.LIGHT -> R.string.settings_theme_light
                                ApplicationSettings.Theme.DARK -> R.string.settings_theme_dark
                                ApplicationSettings.Theme.GREEN -> R.string.settings_theme_green
                            }
                            val selected = (settings as? SettingsState.Loaded)?.settings?.theme == theme
                            Row {
                                RadioButton(
                                    selected = selected,
                                    onClick = {
                                        openDialog = false
                                        viewModel.selectTheme(theme)
                                    }
                                )
                                Text(text = stringResource(id = textId))
                            }
                        }
                    }
                },
                buttons = { }
            )
        }
    }
}
