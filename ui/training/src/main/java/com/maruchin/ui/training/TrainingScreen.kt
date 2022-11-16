@file:OptIn(ExperimentalPagerApi::class)

package com.maruchin.ui.training

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.maruchin.core.ui.*
import com.maruchin.model.plan.samplePlans
import com.maruchin.model.training.sampleTrainings

@Composable
internal fun TrainingScreen(
    state: TrainingUiState,
    onPreviousExercise: () -> Unit,
    onNextExercise: () -> Unit,
    onSelectExercise: (id: String) -> Unit,
    onFinishTraining: () -> Unit,
    onEditSet: (id: String) -> Unit,
) {
    val pagerState = rememberPagerState(state.currentPosition)
    val confirmCompleteDialogState = rememberDialogState()
    ExercisesPagerMediator(pagerState, state, onSelectExercise)
    BackHandler {
        confirmCompleteDialogState.open()
    }
    Scaffold(
        topBar = {
            TrainingAppBar(
                title = state.planDayName,
                onFinish = { confirmCompleteDialogState.open() },
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                TrainingProgressView(exercises = state.exercises, onSelectExercise = onSelectExercise)
                ChangeExerciseView(
                    canGoPrevious = state.canGoPrevious,
                    canGoNext = state.canGoNext,
                    onGoPrevious = onPreviousExercise,
                    onGoNext = onNextExercise,
                )
                HorizontalPager(
                    count = state.exercises.size,
                    modifier = Modifier.weight(1f),
                    state = pagerState,
                ) { page ->
                    val exerciseState = state.exercises[page]
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                    ) {
                        GymsterExerciseView(
                            name = exerciseState.name,
                            numOfSets = exerciseState.numOfSeries,
                            repsRange = exerciseState.repsRange,
                        )
                        SetsListView(
                            sets = exerciseState.sets,
                            onEditSet = onEditSet,
                        )
                        AnimatedVisibility(visible = exerciseState.completed) {
                            ExerciseCompletedView()
                        }
                    }
                }
            }
        }
    }
    ConfirmCompleteDialog(
        dialogState = confirmCompleteDialogState,
        onConfirm = onFinishTraining,
    )
}

@Composable
private fun ExercisesPagerMediator(
    pagerState: PagerState,
    state: TrainingUiState,
    onChangeExercise: (id: String) -> Unit
) {
    val uiPosition = pagerState.currentPage
    val modelPosition = state.currentPosition
    var isScrolling by remember { mutableStateOf(false) }
    val currentOnChangeExercise by rememberUpdatedState(onChangeExercise)
    LaunchedEffect(uiPosition) {
        if (isScrolling && uiPosition == modelPosition) {
            isScrolling = false
        } else if (isScrolling.not() && uiPosition != modelPosition && modelPosition != -1) {
            currentOnChangeExercise(state.exercises[uiPosition].id)
        }
    }
    LaunchedEffect(modelPosition) {
        if (modelPosition != uiPosition && modelPosition != -1) {
            isScrolling = true
            pagerState.animateScrollToPage(modelPosition)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    GymsterTheme {
        TrainingScreen(
            state = TrainingUiState(samplePlans[0], sampleTrainings[0]),
            onPreviousExercise = {},
            onNextExercise = {},
            onSelectExercise = {},
            onFinishTraining = {},
            onEditSet = { }
        )
    }
}
