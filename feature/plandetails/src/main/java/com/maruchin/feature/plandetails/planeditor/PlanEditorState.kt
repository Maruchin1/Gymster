@file:OptIn(ExperimentalMaterialApi::class)

package com.maruchin.feature.plandetails.planeditor

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
internal fun rememberPlanEditorState(sheetState: ModalBottomSheetState): PlanEditorState {
    val scope = rememberCoroutineScope()
    val state = remember(sheetState, scope) {
        PlanEditorState(sheetState, scope)
    }
    LaunchedEffect(sheetState) {
        snapshotFlow { sheetState.isVisible }.collect {
            if (it.not()) state.hide()
        }
    }
    return state
}

internal class PlanEditorState(
    private val sheetState: ModalBottomSheetState,
    private val scope: CoroutineScope,
) {

    var isDisplayed by mutableStateOf(false)
        private set

    var name by mutableStateOf("")

    fun show(name: String) {
        this.name = name
        isDisplayed = true
        scope.launch { sheetState.show() }
    }

    fun hide() {
        scope.launch {
            if (sheetState.isVisible) sheetState.hide()
            isDisplayed = false
            name = ""
        }
    }
}
