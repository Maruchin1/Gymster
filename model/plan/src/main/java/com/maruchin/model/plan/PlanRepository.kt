package com.maruchin.model.plan

import com.maruchin.core.utils.Id
import kotlinx.coroutines.flow.Flow

interface PlanRepository {

    fun getAll(): Flow<List<Plan>>

    fun getById(planId: Id): Flow<Plan?>
}
