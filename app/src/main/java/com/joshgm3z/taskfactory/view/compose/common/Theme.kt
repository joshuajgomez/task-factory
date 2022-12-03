package com.joshgm3z.taskfactory.view.compose.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable

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
    primary = Gray6,
    onPrimary = Gray1,
    secondary = Gray7,
    onSecondary = Gray2,
    tertiary = Gray6,
    onTertiary = Black1,
    tertiaryContainer = Red2,
    onTertiaryContainer = Gray2,
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