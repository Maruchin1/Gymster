@file:OptIn(ExperimentalPagerApi::class)

package com.maruchin.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.*
import com.maruchin.core.ui.GymsterTheme
import com.maruchin.model.plan.samplePlans
import com.maruchin.model.training.sampleTrainings

@Composable
internal fun HomeScreen(
    state: HomeUiState,
    onStartNewWeek: () -> Unit,
    onOpenUserProfile: () -> Unit,
    onStartTraining: (trainingId: String) -> Unit
) {
    Scaffold(
        topBar = {
            HomeAppBar(state = state, onOpenProfile = onOpenUserProfile)
        },
        floatingActionButton = {
            StartNewWeekFab(onClick = onStartNewWeek)
        },
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding)
        ) {
            if (state.weeks.isNotEmpty()) {
                val pagerState = rememberPagerState(state.weeks.lastIndex)
                LaunchedEffect(state.numOfWeeks) {
                    pagerState.animateScrollToPage(state.weeks.lastIndex)
                }
                WeeksTabs(state = state, pagerState = pagerState)
                HorizontalPager(
                    count = state.numOfWeeks,
                    state = pagerState
                ) { page ->
                    WeekPage(state = state.weeks[page], onOpenTraining = onStartTraining)
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    GymsterTheme {
        HomeScreen(
            state = HomeUiState(
                plan = samplePlans.first(),
                trainings = sampleTrainings,
            ),
            onStartNewWeek = {},
            onOpenUserProfile = {},
            onStartTraining = {}
        )
    }
}
