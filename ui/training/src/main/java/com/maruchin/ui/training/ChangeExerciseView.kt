package com.maruchin.ui.training

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.maruchin.feature.training.R

@Composable
internal fun ChangeExerciseView(
    canGoPrevious: Boolean,
    canGoNext: Boolean,
    onGoPrevious: () -> Unit,
    onGoNext: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        OutlinedButton(
            onClick = onGoPrevious,
            enabled = canGoPrevious,
            modifier = Modifier.weight(1f),
        ) {
            Icon(
                imageVector = Icons.Outlined.ChevronLeft,
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
            Text(text = stringResource(R.string.previous).uppercase())
        }
        Spacer(modifier = Modifier.width(12.dp))
        Button(onClick = onGoNext, modifier = Modifier.weight(1f), enabled = canGoNext) {
            Text(text = stringResource(R.string.next).uppercase())
            Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }
    }
}
