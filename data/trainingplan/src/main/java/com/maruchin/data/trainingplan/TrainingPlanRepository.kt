package com.maruchin.data.trainingplan

import com.maruchin.core.utils.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrainingPlanRepository @Inject constructor() {

    private val data = MutableStateFlow(previewTrainingPlans)

    fun getPlansStream(): Flow<List<TrainingPlan>> = data

    fun getPlanStream(planId: String): Flow<TrainingPlan?> =
        data.map { it.findEntity(planId) }

    suspend fun save(plan: TrainingPlan) {
        data.update { it.upsertEntity(plan) }
    }

    suspend fun remove(planId: String) {
        data.update { it.removeEntity(planId) }
    }
}
