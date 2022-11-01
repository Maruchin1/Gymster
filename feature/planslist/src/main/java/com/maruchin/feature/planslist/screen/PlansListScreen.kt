package com.maruchin.feature.planslist.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.maruchin.core.ui.GymsterTheme
import com.maruchin.core.ui.ScreenHeaderView
import com.maruchin.data.trainingplan.previewTrainingPlans

@Composable
internal fun PlansListScreen(
    state: PlansListUiState,
    onNavigateToDetails: (String) -> Unit,
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            ScreenHeaderView(screenName = "Plany treningowe")
            LazyColumn(
                modifier = Modifier.padding(padding)
            ) {
                items(state.plans) {
                    PlanItemView(state = it, onClick = { onNavigateToDetails(it.planId) })
                }
                item {
                    AddPlanPlaceholder(onClick = {})
                }
            }
        }
    }
}

@Composable
private fun PlanItemView(state: PlanUiState, onClick: () -> Unit) {
    Box(
        modifier = Modifier.clickable { onClick() }
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 20.dp)
                    .fillMaxWidth()
            ) {
                Text(text = state.name, style = typography.h5)
            }
            Divider()
        }
    }
}

@Composable
private fun AddPlanPlaceholder(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
            .background(colors.secondary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                tint = colors.onSecondary
            )
            Text(
                text = "Nowy plan",
                style = typography.h6,
                color = colors.onSecondary
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    GymsterTheme {
        PlansListScreen(
            state = PlansListUiState(previewTrainingPlans),
            onNavigateToDetails = {}
        )
    }
}
