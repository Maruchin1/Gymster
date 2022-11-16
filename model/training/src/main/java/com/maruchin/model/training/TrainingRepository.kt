package com.maruchin.model.training

import kotlinx.coroutines.flow.Flow

interface TrainingRepository {

    fun getByPlan(planId: String): Flow<List<Training>>

    fun getActive(): Flow<Training?>

    fun getPrevious(): Flow<Training?>

    suspend fun setActive(trainingId: String?)

    suspend fun save(training: Training)
}
