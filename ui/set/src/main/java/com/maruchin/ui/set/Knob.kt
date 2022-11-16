@file:OptIn(ExperimentalComposeUiApi::class)

package com.maruchin.ui.set

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StampedPathEffectStyle
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.pointerInteropFilter
import kotlin.math.PI

private const val STROKE_WIDTH = 82f
private const val DASH_RATIO = 3f / 4f
private const val GAP_RATIO = 1 - DASH_RATIO
private const val NUM_OF_SEGMENTS = 16

@Composable
internal fun Knob(knobState: KnobState, modifier: Modifier = Modifier) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxWidth()
    ) {
        val primaryColor = colors.primary
        Canvas(
            modifier = modifier
                .size(maxWidth)
                .pointerInteropFilter { event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            knobState.startMove(event)
                            true
                        }
                        MotionEvent.ACTION_MOVE -> {
                            knobState.move(event)
                            true
                        }
                        MotionEvent.ACTION_UP -> {
                            knobState.endMove()
                            true
                        }
                        else -> false
                    }
                }
        ) {
            knobState.center = center
            rotate(knobState.angle.toFloat()) {
                val radius = (size.minDimension / 2) - STROKE_WIDTH
                val circumference = (2 * PI * radius).toFloat()
                val dash = (circumference * DASH_RATIO) / NUM_OF_SEGMENTS
                val gap = (circumference * GAP_RATIO) / NUM_OF_SEGMENTS
                drawCircle(
                    color = primaryColor,
                    style = Stroke(
                        pathEffect = PathEffect.stampedPathEffect(
                            shape = Path().apply {
                                addRoundRect(
                                    RoundRect(
                                        left = 0f,
                                        top = 0f,
                                        right = dash,
                                        bottom = 82f,
                                        radiusX = 20f,
                                        radiusY = 20f
                                    )
                                )
                            },
                            style = StampedPathEffectStyle.Morph,
                            phase = 0f,
                            advance = dash + gap
                        )
                    ),
                    radius = (size.minDimension / 2) - STROKE_WIDTH
                )
            }
        }
    }
}
