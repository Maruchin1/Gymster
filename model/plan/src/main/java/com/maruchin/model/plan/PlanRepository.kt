package com.maruchin.model.plan

import kotlinx.coroutines.flow.Flow

interface PlanRepository {

    fun getById(planId: String): Flow<Plan?>
}
