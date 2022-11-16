package com.maruchin.ui.training

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FitnessCenter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.maruchin.core.ui.GymsterCard

@Composable
internal fun SetsListView(sets: List<SetUiState>, onEditSet: (id: String) -> Unit) {
    GymsterCard(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxWidth()
    ) {
        Column {
            sets.forEachIndexed { index, state ->
                SetView(state = state, onEditSet = { onEditSet(state.id) })
                if (index != sets.lastIndex) {
                    Divider()
                }
            }
        }
    }
}

@Composable
private fun SetView(state: SetUiState, onEditSet: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onEditSet() }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Outlined.FitnessCenter,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = "Set ${state.id}.")
        Spacer(modifier = Modifier.weight(1f))
        Row {
            Text(
                text = state.weight,
                style = typography.body1,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "x",
                style = typography.body1,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = state.reps,
                style = typography.body1,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.width(12.dp))
        }
    }
}
