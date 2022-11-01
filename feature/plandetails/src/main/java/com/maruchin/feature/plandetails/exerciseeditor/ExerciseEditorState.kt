@file:OptIn(ExperimentalMaterialApi::class)

package com.maruchin.feature.plandetails.exerciseeditor

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun rememberExerciseEditorState(sheetState: ModalBottomSheetState): ExerciseEditorState {
    val scope = rememberCoroutineScope()
    val state = remember(sheetState, scope) {
        ExerciseEditorState(sheetState, scope)
    }
    LaunchedEffect(sheetState) {
        snapshotFlow { sheetState.isVisible }.collect {
            if (it.not()) state.hide()
        }
    }
    return state
}

internal class ExerciseEditorState(
    private val sheetState: ModalBottomSheetState,
    private val scope: CoroutineScope,
) {

    var isDisplayed by mutableStateOf(false)
        private set

    var trainingId by mutableStateOf("")
        private set

    var exerciseId by mutableStateOf("")
        private set

    var name by mutableStateOf("")

    var numOfSeries by mutableStateOf("")

    var minReps by mutableStateOf("")

    var maxReps by mutableStateOf("")

    fun show(
        trainingId: String,
        exerciseId: String,
        name: String,
        numOfSeries: String,
        minReps: String,
        maxReps: String,
    ) {
        this.trainingId = trainingId
        this.exerciseId = exerciseId
        this.name = name
        this.numOfSeries = numOfSeries
        this.minReps = minReps
        this.maxReps = maxReps
        isDisplayed = true
        scope.launch { sheetState.show() }
    }

    fun hide() {
        scope.launch {
            if (sheetState.isVisible) sheetState.hide()
            isDisplayed = false
            trainingId = ""
            exerciseId = ""
            name = ""
            numOfSeries = ""
            minReps = ""
            maxReps = ""
        }
    }
}
