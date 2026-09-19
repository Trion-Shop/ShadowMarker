package com.betwinner.shadowmarker.core.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// BETWINNER SIGNATURE PALETTE (Cover-shadow defensive nuance)
val ShadowGreenDark = Color(0xFF112D20)
val ShadowGreenPitch = Color(0xFF1E523A)
val ShadowGreenElevated = Color(0xFF2C7050)
val ShadowGreenBorder = Color(0xFF45946E)
val ShadowGreenNeon = Color(0xFF4EE298)

val ShadowGold = Color(0xFFF6CB45)
val ShadowGoldBright = Color(0xFFFFDE72)
val ShadowGoldAmber = Color(0xFFDCAE26)

val ShadowWhite = Color(0xFFFFFFFF)
val ShadowMutedGreen = Color(0xFFBFE0D1)
val ShadowSoftGray = Color(0xFFE5EEE9)

val ShadowBgGradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFF1A4330),
        ShadowGreenPitch,
        ShadowGreenDark
    )
)
