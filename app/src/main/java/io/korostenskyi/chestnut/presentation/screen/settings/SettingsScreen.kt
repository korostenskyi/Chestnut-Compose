package io.korostenskyi.chestnut.presentation.screen.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.korostenskyi.chestnut.R
import io.korostenskyi.chestnut.domain.model.ApplicationSettings
import io.korostenskyi.chestnut.presentation.composables.SettingsButton

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel()) {
    val settings = viewModel.settingsFlow.collectAsState().value
    var openThemeDialog by remember { mutableStateOf(false) }
    var openResetConfirmationDialog by remember { mutableStateOf(false) }
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
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.action_back),
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
                    )
                }
            }
        )
        SettingsButton(
            text = {
                Text(text = stringResource(id = R.string.settings_theme))
            },
            onClick = {
                openThemeDialog = true
            }
        )
        Divider(color = MaterialTheme.colors.onBackground)
        SettingsButton(
            text = {
                Text(
                    text = stringResource(id = R.string.action_reset),
                    color = MaterialTheme.colors.error
                )
            },
            onClick = {
                openResetConfirmationDialog = true
            }
        )
        Divider(color = MaterialTheme.colors.onBackground)
        if (openThemeDialog) {
            AlertDialog(
                onDismissRequest = { openThemeDialog = false },
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
                                ApplicationSettings.Theme.PURPLE -> R.string.settings_theme_purple
                            }
                            val selected = (settings as? SettingsState.Loaded)?.settings?.theme == theme
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.clickable {
                                    openThemeDialog = false
                                    viewModel.selectTheme(theme)
                                }
                            ) {
                                RadioButton(
                                    selected = selected,
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = MaterialTheme.colors.secondary,
                                        unselectedColor = MaterialTheme.colors.onBackground.copy(alpha = 0.6f),
                                        disabledColor = MaterialTheme.colors.onBackground.copy(alpha = ContentAlpha.disabled)
                                    ),
                                    onClick = {
                                        openThemeDialog = false
                                        viewModel.selectTheme(theme)
                                    }
                                )
                                Spacer(modifier = Modifier.size(4.dp))
                                Text(text = stringResource(id = textId))
                            }
                        }
                    }
                },
                buttons = { }
            )
        }
        if (openResetConfirmationDialog) {
            AlertDialog(
                onDismissRequest = { openResetConfirmationDialog = false },
                title = {
                    Text(text = stringResource(id = R.string.settings_reset_confirmation_title))
                },
                text = {
                    Text(text = stringResource(id = R.string.settings_reset_confirmation_description))
                },
                buttons = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.secondary),
                            onClick = { openResetConfirmationDialog = false }
                        ) {
                            Text(text = stringResource(id = R.string.label_no))
                        }
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.error),
                            onClick = {
                                openResetConfirmationDialog = false
                                viewModel.reset()
                            }
                        ) {
                            Text(
                                text = stringResource(id = R.string.label_yes),
                                color = MaterialTheme.colors.onError
                            )
                        }
                    }
                }
            )
        }
    }
}
