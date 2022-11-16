package com.maruchin.ui.training

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.maruchin.core.ui.divider

@Composable
internal fun TrainingProgressView(
    exercises: List<ExerciseUiState>,
    onSelectExercise: (String) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        exercises.forEach { state ->
            ProgressItem(
                exerciseNumber = state.id,
                completed = state.completed,
                active = state.active,
                onSelect = { onSelectExercise(state.id) }
            )
        }
    }
}

@Composable
private fun RowScope.ProgressItem(
    exerciseNumber: String,
    completed: Boolean,
    active: Boolean,
    onSelect: () -> Unit
) {
    Card(
        modifier = Modifier
            .weight(1f)
            .height(48.dp),
        backgroundColor = when {
            completed -> colors.secondary
            else -> colors.background
        },
        border = BorderStroke(
            width = if (active) 3.dp else 1.dp,
            color = when {
                active && completed -> colors.primaryVariant
                active -> colors.primary
                else -> colors.divider
            }
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onSelect() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = exerciseNumber,
                style = typography.subtitle1,
                color = when {
                    active && completed -> colors.primaryVariant
                    active -> colors.primary
                    completed -> colors.onSecondary
                    else -> colors.onBackground
                },
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}
