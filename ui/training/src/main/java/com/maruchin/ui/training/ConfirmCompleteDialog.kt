package com.maruchin.ui.training

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.maruchin.core.ui.DialogState
import com.maruchin.core.ui.rememberDialogState
import com.maruchin.feature.training.R

@Composable
internal fun ConfirmCompleteDialog(
    dialogState: DialogState = rememberDialogState(),
    onConfirm: () -> Unit,
) {
    if (dialogState.isOpen.not()) return
    AlertDialog(
        onDismissRequest = { dialogState.dismiss() },
        title = {
            Text(text = stringResource(R.string.finish_training))
        },
        text = {
            Text(text = stringResource(R.string.finish_training_explanation))
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirm()
                    dialogState.dismiss()
                },
            ) {
                Text(text = stringResource(R.string.confirm).uppercase())
            }
        },
        dismissButton = {
            OutlinedButton(
                onClick = { dialogState.dismiss() }
            ) {
                Text(text = stringResource(R.string.cancel).uppercase())
            }
        }
    )
}
