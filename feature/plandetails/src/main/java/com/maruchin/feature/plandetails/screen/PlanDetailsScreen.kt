@file:OptIn(ExperimentalMaterialApi::class)

package com.maruchin.feature.plandetails.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maruchin.core.ui.GymsterTheme
import com.maruchin.core.ui.ScreenHeaderView
import com.maruchin.data.trainingplan.previewTrainingPlans
import com.maruchin.feature.plandetails.exerciseeditor.ExerciseEditorView
import com.maruchin.feature.plandetails.exerciseeditor.rememberExerciseEditorState
import com.maruchin.feature.plandetails.planeditor.PlanEditorView
import com.maruchin.feature.plandetails.planeditor.rememberPlanEditorState
import com.maruchin.feature.plandetails.trainingeditor.TrainingEditorView
import com.maruchin.feature.plandetails.trainingeditor.rememberTrainingEditorState

@Composable
internal fun PlanDetailsScreen(
    state: PlanDetailsUiState,
    onModifyPlan: (name: String) -> Unit,
    onModifyTraining: (trainingId: String, name: String) -> Unit,
    onModifyExercise: (
        trainingId: String,
        exerciseId: String,
        name: String,
        numOfSeries: String,
        minReps: String,
        maxReps: String
    ) -> Unit,
    onNavigateBack: () -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )
    val planEditorState = rememberPlanEditorState(sheetState)
    val trainingEditorState = rememberTrainingEditorState(sheetState)
    val exerciseEditorState = rememberExerciseEditorState(sheetState)

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            Spacer(modifier = Modifier.height(1.dp))
            PlanEditorView(
                state = planEditorState,
                onSave = { onModifyPlan(planEditorState.name) }
            )
            TrainingEditorView(
                state = trainingEditorState,
                onSave = {
                    onModifyTraining(
                        trainingEditorState.trainingId,
                        trainingEditorState.name
                    )
                }
            )
            ExerciseEditorView(
                state = exerciseEditorState,
                onSave = {
                    onModifyExercise(
                        exerciseEditorState.trainingId,
                        exerciseEditorState.exerciseId,
                        exerciseEditorState.name,
                        exerciseEditorState.numOfSeries,
                        exerciseEditorState.minReps,
                        exerciseEditorState.maxReps
                    )
                }
            )
        }
    ) {
        Scaffold { padding ->
            Column(
                modifier = Modifier.padding(padding)
            ) {
                ScreenHeaderView(
                    screenName = state.name,
                    onNavigateUp = onNavigateBack,
                    onOpenOptions = {},
                )
                LazyColumn(
                    modifier = Modifier.padding(padding)
                ) {
                    items(state.trainings) { trainingState ->
                        TrainingView(
                            state = trainingState,
                            onEditTraining = {
                                trainingEditorState.show(
                                    trainingState.trainingId,
                                    trainingState.name,
                                )
                            },
                            onEditExercise = {
                                exerciseEditorState.show(
                                    trainingState.trainingId,
                                    it.exerciseId,
                                    it.name,
                                    it.numOfSeries,
                                    it.minReps,
                                    it.maxReps
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TrainingView(
    state: TrainingUiState,
    onEditTraining: () -> Unit,
    onEditExercise: (ExerciseUiState) -> Unit
) {
    Column {
        Box(
            modifier = Modifier.clickable { onEditTraining() }
        ) {
            Text(
                text = state.name,
                style = typography.h6,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
        state.exercises.forEach { exerciseState ->
            ExerciseView(
                state = exerciseState,
                onEditExercise = { onEditExercise(exerciseState) }
            )
        }
        Box(
            modifier = Modifier
                .height(8.dp)
                .background(colors.secondaryVariant)
                .fillMaxWidth(),
        )
    }
}

@Composable
private fun ExerciseView(state: ExerciseUiState, onEditExercise: () -> Unit) {
    Column {
        Divider()
        Box(
            modifier = Modifier.clickable { onEditExercise() }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = state.name, style = typography.body1)
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "${state.numOfSeries} serie, ${state.minReps}-${state.maxReps} powtórzeń",
                    style = typography.body2
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    GymsterTheme {
        PlanDetailsScreen(
            state = PlanDetailsUiState(previewTrainingPlans[1]),
            onModifyPlan = {},
            onModifyTraining = { _, _ -> },
            onModifyExercise = { _, _, _, _, _, _ -> },
            onNavigateBack = {},
        )
    }
}
