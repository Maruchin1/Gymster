package com.maruchin.model.training

import com.maruchin.core.utils.Id
import kotlinx.coroutines.flow.Flow

interface TrainingRepository {

    fun getByPlan(planId: Id): Flow<List<Training>>

    fun getActive(): Flow<Training?>

    fun getPrevious(): Flow<Training?>

    suspend fun setActive(trainingId: Id)

    suspend fun add(training: Training)

    suspend fun update(training: Training)

    suspend fun delete(training: Training)
}
