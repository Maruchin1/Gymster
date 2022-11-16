package com.maruchin.gymster

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.maruchin.ui.training.navigateToTraining
import com.maruchin.ui.training.trainingScreen
import com.maruchin.ui.home.HOME_DESTINATION
import com.maruchin.ui.home.homeScreen
import com.maruchin.ui.set.navigateToSet
import com.maruchin.ui.set.setScreen

@Composable
internal fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(navController, HOME_DESTINATION) {
        homeScreen(
            onNavigateToUser = {},
            onNavigateToTraining = { navController.navigateToTraining() }
        )
        trainingScreen(
            onNavigateBack = { navController.popBackStack() },
            onNavigateToSet = { navController.navigateToSet() }
        )
        setScreen(
            onNavigateBack = { navController.popBackStack() }
        )
    }
}
