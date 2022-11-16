@file:OptIn(ExperimentalPagerApi::class)

package com.maruchin.ui.home

import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@Composable
internal fun WeeksTabs(state: HomeUiState, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabsPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabsPositions)
            )
        },
        backgroundColor = colors.background,
        edgePadding = 0.dp
    ) {
        state.weeks.forEachIndexed { index, week ->
            Tab(
                selected = index == pagerState.currentPage,
                onClick = {
                    scope.launch { pagerState.animateScrollToPage(index) }
                },
                text = { Text(text = "${stringResource(R.string.week).uppercase()} ${week.weekNumber}") }
            )
        }
    }
}
