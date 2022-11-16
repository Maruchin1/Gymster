package com.maruchin.ui.set

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.setScreen(onNavigateBack: () -> Unit) {
    composable("set") {
        val viewModel = hiltViewModel<SetViewModel>()
        val state by viewModel.uiState.collectAsState()
        SetScreen(
            state = state,
            onCompleteSet = viewModel::completeSet,
            onBack = onNavigateBack
        )
    }
}

fun NavController.navigateToSet() {
    navigate("set")
}
