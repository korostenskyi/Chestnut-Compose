package io.korostenskyi.chestnut.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import io.korostenskyi.chestnut.domain.model.ApplicationSettings

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Green400
)

private val LightColorPalette = lightColors(
    primary = Color.White,
    secondary = Green400,
    onBackground = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

private val PurpleColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Green400,
    onBackground = Color.Black,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onSurface = Color.White
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
        ApplicationSettings.Theme.PURPLE -> PurpleColorPalette
    }
    uiController.setStatusBarColor(color = colors.primarySurface)
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}