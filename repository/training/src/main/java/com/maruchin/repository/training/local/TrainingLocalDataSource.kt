package com.maruchin.repository.training.local

import com.maruchin.core.utils.Id
import com.maruchin.core.utils.IoDispatcher
import com.maruchin.model.training.Training
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class TrainingLocalDataSource @Inject constructor(
    private val dao: TrainingDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    fun getByPlan(planId: Id): Flow<List<Training>> =
        dao.getByPlan(planId.value).map { list -> list.map { it.asModel() } }

    fun getById(id: Id): Flow<Training?> =
        dao.getById(id.value).map { it.asModel() }

    fun getPrevious(training: Training): Flow<Training?> =
        dao.getByWeek(
            planId = training.planId.value,
            planTrainingId = training.planTrainingId.value,
            weekNumber = training.weekNumber - 1
        ).map { it.firstOrNull()?.asModel() }


    suspend fun add(training: Training) = withContext(dispatcher) {
        dao.add(training.asDb())
    }

    suspend fun update(training: Training) = withContext(dispatcher) {
        dao.update(training.asDb())
    }

    suspend fun delete(training: Training) = withContext(dispatcher) {
        dao.delete(training.asDb())
    }
}
