package com.maruchin.core.ui

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import java.text.DecimalFormat

fun formatRepsRange(repsRange: IntRange): String =
    "${repsRange.first} - ${repsRange.last}"

fun formatWeight(weight: Float): String =
    "${DecimalFormat().format(weight)} kg"

@Composable
fun formatExerciseSpecification(numOfSeries: String, repsRange: String) = buildAnnotatedString {
    val highlightStyle = SpanStyle(
        color = MaterialTheme.colors.primary,
        fontWeight = FontWeight.SemiBold,
    )
    withStyle(highlightStyle) {
        append(numOfSeries)
    }
    append(" sets of ")
    withStyle(highlightStyle) {
        append(repsRange)
    }
    append(" reps")
}
