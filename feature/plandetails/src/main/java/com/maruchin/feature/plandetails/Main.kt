package com.maruchin.feature.plandetails

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maruchin.feature.plandetails.screen.PlanDetailsScreen
import com.maruchin.feature.plandetails.screen.PlanDetailsViewModel

fun NavGraphBuilder.planDetailsScreen(onNavigateBack: () -> Unit) {
    composable("plan-details/{planId}") {
        val planId = it.arguments?.getString("planId")
        val viewModel = hiltViewModel<PlanDetailsViewModel>()
        val state by viewModel.uiState.collectAsState()
        LaunchedEffect(planId) {
            if (planId != null) viewModel.selectPlan(planId)
        }
        PlanDetailsScreen(
            state = state,
            onModifyPlan = viewModel::modifyPlan,
            onModifyTraining = viewModel::modifyTraining,
            onModifyExercise = viewModel::modifyExercise,
            onNavigateBack = onNavigateBack,
        )
    }
}

fun NavController.navigateToPlanDetails(planId: String) {
    navigate("plan-details/$planId")
}
