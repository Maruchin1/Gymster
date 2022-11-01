package com.maruchin.feature.plandetails.exerciseeditor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
internal fun ExerciseEditorView(
    state: ExerciseEditorState,
    onSave: () -> Unit,
) {
    if (state.isDisplayed) {
        Surface {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Cwiczenie", style = typography.h6)
                Spacer(modifier = Modifier.height(32.dp))
                TextField(
                    label = { Text(text = "Nazwa") },
                    value = state.name,
                    onValueChange = { state.name = it },
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    label = { Text(text = "Liczba serii") },
                    value = state.numOfSeries,
                    onValueChange = { state.numOfSeries = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    label = { Text(text = "Minimalna liczba powtórzeń") },
                    value = state.minReps,
                    onValueChange = { state.minReps = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    label = { Text(text = "Maksymalna liczba powtórzeń") },
                    value = state.maxReps,
                    onValueChange = { state.maxReps = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth(),
                )
                Spacer(modifier = Modifier.height(48.dp))
                Button(
                    onClick = onSave,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Zapisz")
                }
            }
        }
    }
}
