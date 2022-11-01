package com.maruchin.feature.planslist

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.maruchin.feature.planslist.screen.PlansListScreen
import com.maruchin.feature.planslist.screen.PlansListViewModel

fun NavGraphBuilder.plansListScreen(onNavigateToDetails: (String) -> Unit) {
    composable("plans-list") {
        val viewModel = hiltViewModel<PlansListViewModel>()
        val state by viewModel.state.collectAsState()
        PlansListScreen(state = state, onNavigateToDetails = onNavigateToDetails)
    }
}

fun NavController.navigateToPlanList() {
    navigate("plans-list")
}
