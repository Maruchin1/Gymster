package com.maruchin.core.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GymsterTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = darkColors(
            primary = Color(0xffFFD369),
            primaryVariant = Color(0xfff99f3b),
            onPrimary = Color.Black,
            secondary = Color(0xffEEEEEE),
            secondaryVariant = Color(0xff616161),
            onSecondary = Color.Black,
            background = Color(0xff222831),
            onBackground = Color.White,
            surface = Color(0xff393E46),
            onSurface = Color.White,
        ),
        shapes = MaterialTheme.shapes.copy(
            small = RoundedCornerShape(16.dp),
            medium = RoundedCornerShape(12.dp),
            large = RoundedCornerShape(0.dp)
        ),
        content = content
    )
}

val Colors.divider: Color
    @Composable
    get() = onSurface.copy(alpha = 0.12f)
