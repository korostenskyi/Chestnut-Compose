package io.korostenskyi.chestnut.presentation.theme

import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.material.primarySurface
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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

private val PurpleColorPalette = lightColorScheme(
    primary = Purple500,
    primaryContainer = Purple700,
    secondary = Green400,
    onBackground = Color.Black,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onSurface = Color.White
)

private val lightColorScheme = lightColorScheme(
    primary = Color.White,
    secondary = Green400,
    onBackground = Color.Black,
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onSurface = Color.Black,
    onError = Color.White
)

private val darkColorScheme = darkColorScheme(
    primary = Purple200,
    primaryContainer = Purple700,
    secondary = Green400
)

@SuppressLint("NewApi")
@Composable
fun ChestnutTheme(
    theme: ApplicationSettings.Theme,
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamic: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val uiController = rememberSystemUiController()
    val colors = when (theme) {
        ApplicationSettings.Theme.SYSTEM -> if (dynamic) {
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        } else {
            if (darkTheme) darkColorScheme else lightColorScheme
        }
        ApplicationSettings.Theme.DARK -> darkColorScheme
        ApplicationSettings.Theme.LIGHT -> lightColorScheme
        ApplicationSettings.Theme.PURPLE -> PurpleColorPalette
    }
    with(uiController) {
        setStatusBarColor(color = colors.surface)
        setNavigationBarColor(color = colors.surface)
    }
    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}