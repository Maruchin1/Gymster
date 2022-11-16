package com.maruchin.repository.plan

import com.maruchin.core.utils.findEntity
import com.maruchin.model.plan.Plan
import com.maruchin.model.plan.PlanRepository
import com.maruchin.model.plan.samplePlans
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class MemoryPlanRepository @Inject constructor() : PlanRepository {

    private val collection = MutableStateFlow(samplePlans)

    override fun getById(planId: String): Flow<Plan?> =
        collection.map { it.findEntity(planId) }
}
