package com.maruchin.gymster

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.maruchin.feature.plandetails.navigateToPlanDetails
import com.maruchin.feature.plandetails.planDetailsScreen
import com.maruchin.feature.planslist.plansListScreen

@Composable
internal fun MainNavigator() {
    val navController = rememberNavController()
    NavHost(navController, "plans-list") {
        plansListScreen(
            onNavigateToDetails = {
                navController.navigateToPlanDetails(it)
            }
        )
        planDetailsScreen(
            onNavigateBack = { navController.popBackStack() }
        )
    }
}
