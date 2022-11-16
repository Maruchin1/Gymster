package com.maruchin.ui.training

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.trainingScreen(
    onNavigateBack: () -> Unit,
    onNavigateToSet: () -> Unit
) {
    composable("training") {
        val viewModel = hiltViewModel<TrainingViewModel>()
        val state by viewModel.uiState.collectAsState()
        TrainingScreen(
            state = state,
            onPreviousExercise = viewModel::goToPreviousExercise,
            onNextExercise = viewModel::goToNextExercise,
            onSelectExercise = viewModel::goToExercise,
            onFinishTraining = onNavigateBack,
            onEditSet = { position ->
                viewModel.editSet(position)
                onNavigateToSet()
            },
        )
    }
}

fun NavController.navigateToTraining() {
    navigate("training")
}
