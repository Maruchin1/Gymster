package com.maruchin.core.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GymsterExerciseView(name: String, numOfSets: String, repsRange: String) {
    GymsterCard(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = name, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = formatExerciseSpecification(numOfSets, repsRange),
                style = MaterialTheme.typography.body1
            )
        }
    }
}
