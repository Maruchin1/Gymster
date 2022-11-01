package com.maruchin.core.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Color(0xffF50000),
    onPrimary = Color.White,
    primaryVariant = Color(0xffF50000),
    secondary = Color(0xff000000),
    onSecondary = Color.White,
    secondaryVariant = Color(0xffBABABA),
)

@Composable
fun GymsterTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
