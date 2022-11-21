package com.maruchin.repository.training

import com.maruchin.core.utils.Id
import com.maruchin.model.training.Training
import com.maruchin.model.training.TrainingRepository
import com.maruchin.repository.training.local.TrainingLocalDataSource
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class TrainingRepositoryImpl @Inject constructor(
    private val localDataSource: TrainingLocalDataSource
) : TrainingRepository {

    private val activeId = MutableStateFlow(Id.empty)

    override fun getByPlan(planId: Id): Flow<List<Training>> =
        localDataSource.getByPlan(planId)

    override fun getActive(): Flow<Training?> =
        activeId.flatMapLatest { localDataSource.getById(it) }

    override fun getPrevious(): Flow<Training?> =
        getActive().flatMapLatest {
            if (it != null) localDataSource.getPrevious(it)
            else flowOf(null)
        }

    override suspend fun setActive(trainingId: Id) {
        activeId.emit(trainingId)
    }

    override suspend fun add(training: Training) {
        localDataSource.add(training)
    }

    override suspend fun update(training: Training) {
        localDataSource.update(training)
    }

    override suspend fun delete(training: Training) {
        localDataSource.delete(training)
    }
}
