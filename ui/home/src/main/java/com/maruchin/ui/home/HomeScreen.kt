@file:OptIn(ExperimentalPagerApi::class)

package com.maruchin.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.*
import com.maruchin.core.utils.Id

@Composable
internal fun HomeScreen(
    state: HomeUiState,
    onStartNewWeek: () -> Unit,
    onOpenUserProfile: () -> Unit,
    onStartTraining: (Id) -> Unit
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
