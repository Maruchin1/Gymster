@file:OptIn(ExperimentalMaterialApi::class)

package com.maruchin.feature.plandetails.trainingeditor

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun rememberTrainingEditorState(sheetState: ModalBottomSheetState): TrainingEditorState {
    val scope = rememberCoroutineScope()
    val state = remember(sheetState, scope) {
        TrainingEditorState(sheetState, scope)
    }
    LaunchedEffect(sheetState) {
        snapshotFlow { sheetState.isVisible }.collect {
            if (it.not()) state.hide()
        }
    }
    return state
}

internal class TrainingEditorState(
    private val sheetState: ModalBottomSheetState,
    private val scope: CoroutineScope,
) {

    var isDisplayed by mutableStateOf(false)
        private set

    var trainingId by mutableStateOf("")
        private set

    var name by mutableStateOf("")

    fun show(trainingId: String, name: String) {
        this.trainingId = trainingId
        this.name = name
        isDisplayed = true
        scope.launch { sheetState.show() }
    }

    fun hide() {
        scope.launch {
            if (sheetState.isVisible) sheetState.hide()
            isDisplayed = false
            trainingId = ""
            name = ""
        }
    }
}
