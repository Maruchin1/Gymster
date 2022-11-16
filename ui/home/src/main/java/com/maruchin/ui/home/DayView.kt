package com.maruchin.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.maruchin.core.ui.GymsterCard

@Composable
internal fun DayView(state: DayUiState, onOpenTraining: (String) -> Unit) {
    GymsterCard(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 8.dp),
            ) {
                Text(
                    text = state.dayName,
                    style = typography.subtitle2,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
                TextButton(
                    onClick = { onOpenTraining(state.trainingId) }
                ) {
                    Text(text = stringResource(R.string.begin).uppercase())
                    Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                    Icon(
                        imageVector = Icons.Outlined.ChevronRight,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }
            }
            Divider()
            state.exercises.forEachIndexed { index, exercise ->
                ExerciseView(state = exercise)
                if (index != state.exercises.lastIndex) Divider()
            }
        }
    }
}
