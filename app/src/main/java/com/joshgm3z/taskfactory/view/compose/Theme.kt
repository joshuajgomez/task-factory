package com.joshgm3z.taskfactory.view.compose

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.UiMode

val DarkColorPalette = darkColorScheme(
    primary = Gray1,
    onPrimary = White1,
    secondary = Gray2,
    onSecondary = White1,
    tertiary = Gray20,
    onTertiary = White1,
    tertiaryContainer = Red1,
    onTertiaryContainer = White1,
    primaryContainer = Green1,
    onPrimaryContainer = White1,
    secondaryContainer = Red1,
    onSecondaryContainer = White1,
)

val LightColorPalette = lightColorScheme(
    primary = White1,
    onPrimary = Gray1,
    secondary = Gray6,
    onSecondary = Gray1,
    tertiary = Green3,
    onTertiary = Black1,
    tertiaryContainer = Red2,
    onTertiaryContainer = White1,
    primaryContainer = Green2,
    onPrimaryContainer = Black1,
    secondaryContainer = Red2,
    onSecondaryContainer = White1,
)

@Composable
fun Material3AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
//    val useDynamicColors = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
//    val colors = when {
//        useDynamicColors && darkTheme -> dynamicDarkColorScheme(LocalContext.current)
//        useDynamicColors && !darkTheme -> dynamicLightColorScheme(LocalContext.current)
//        darkTheme -> DarkColorPalette
//        else -> TODO()
//    }

    val colors = when {
        darkTheme -> DarkColorPalette
        else -> LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}