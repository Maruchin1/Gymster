package com.maruchin.feature.plandetails.planeditor

import androidx.compose.runtime.Composable
import com.maruchin.feature.plandetails.nameeditor.NameEditorModal

@Composable
internal fun PlanEditorView(
    state: PlanEditorState,
    onSave: () -> Unit,
) {
    if (state.isDisplayed) {
        NameEditorModal(
            title = "Edytuj plan",
            name = state.name,
            onChangeName = { state.name = it },
            onSave = {
                onSave()
                state.hide()
            }
        )
    }
}
