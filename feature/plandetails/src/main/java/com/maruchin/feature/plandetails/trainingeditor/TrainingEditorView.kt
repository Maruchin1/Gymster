package com.maruchin.feature.plandetails.trainingeditor

import androidx.compose.runtime.Composable
import com.maruchin.feature.plandetails.nameeditor.NameEditorModal

@Composable
internal fun TrainingEditorView(
    state: TrainingEditorState,
    onSave: () -> Unit
) {
    if (state.isDisplayed) {
        NameEditorModal(
            title = "Edytuj trening",
            name = state.name,
            onChangeName = { state.name = it },
            onSave = {
                onSave()
                state.hide()
            }
        )
    }
}
