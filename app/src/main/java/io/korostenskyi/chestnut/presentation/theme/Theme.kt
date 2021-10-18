package io.korostenskyi.chestnut.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.korostenskyi.chestnut.domain.model.ApplicationSettings

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val GreenColorPalette = lightColors(
    primary = Green800,
    primaryVariant = Green900,
    secondary = Green400
)

@Composable
fun ChestnutTheme(
    theme: ApplicationSettings.Theme,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val uiController = rememberSystemUiController()
    val colors = when (theme) {
        ApplicationSettings.Theme.SYSTEM -> if (darkTheme) {
            DarkColorPalette
        } else {
            LightColorPalette
        }
        ApplicationSettings.Theme.DARK -> DarkColorPalette
        ApplicationSettings.Theme.LIGHT -> LightColorPalette
        ApplicationSettings.Theme.GREEN -> GreenColorPalette
    }
    uiController.setStatusBarColor(
        color = colors.primarySurface,
        darkIcons = !colors.isLight
    )
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}