package com.maruchin.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.maruchin.core.ui.formatExerciseSpecification

@Composable
internal fun ExerciseView(state: ExerciseUiState) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp)
    ) {
        Text(text = state.exerciseName, style = typography.body1)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = formatExerciseSpecification(
                numOfSeries = state.numOfSeries,
                repsRange = state.repsRange
            ),
            style = typography.caption,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            state.sets.forEach { set ->
                SetView(state = set)
            }
        }
    }
}

@Composable
internal fun SetView(state: SetUiState) {
    Row {
        Text(
            text = state.weight,
            style = typography.body2,
            fontWeight = FontWeight.Medium,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "x", style = typography.body2)
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = state.reps,
            style = typography.body2,
            fontWeight = FontWeight.Medium,
        )
    }
}
