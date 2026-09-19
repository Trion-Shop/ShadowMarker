package com.betwinner.shadowmarker.core.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = ShadowGold,
    secondary = ShadowGreenNeon,
    tertiary = ShadowGoldBright,
    background = ShadowGreenDark,
    surface = ShadowGreenPitch,
    onPrimary = ShadowGreenDark,
    onSecondary = ShadowWhite,
    onBackground = ShadowWhite,
    onSurface = ShadowWhite
)

@Composable
fun ShadowMarkerTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        content = content
    )
}
