package com.maruchin.feature.plandetails.nameeditor

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
internal fun NameEditorModal(
    title: String,
    name: String,
    onChangeName: (String) -> Unit,
    onSave: () -> Unit
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = title, style = typography.h6)
            Spacer(modifier = Modifier.height(32.dp))
            TextField(
                value = name,
                onValueChange = onChangeName,
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

@Preview
@Composable
private fun Preview() {
    NameEditorModal(
        title = "Nazwa planu",
        name = "Push Pull",
        onChangeName = {},
        onSave = {},
    )
}
