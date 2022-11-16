package com.maruchin.ui.home

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.homeScreen(
    onNavigateToUser: () -> Unit,
    onNavigateToTraining: () -> Unit
) {
    composable("home") {
        val viewModel = hiltViewModel<HomeViewModel>()
        val state by viewModel.uiState.collectAsState()
        HomeScreen(
            state = state,
            onStartNewWeek = viewModel::startNewWeek,
            onOpenUserProfile = onNavigateToUser,
            onStartTraining = { trainingId ->
                viewModel.startTraining(trainingId)
                onNavigateToTraining()
            },
        )
    }
}

const val HOME_DESTINATION = "home"
