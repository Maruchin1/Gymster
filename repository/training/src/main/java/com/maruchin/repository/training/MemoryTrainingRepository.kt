package com.maruchin.repository.training

import com.maruchin.core.utils.findEntity
import com.maruchin.core.utils.upsertEntity
import com.maruchin.model.training.Training
import com.maruchin.model.training.TrainingRepository
import com.maruchin.model.training.sampleTrainings
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class MemoryTrainingRepository @Inject constructor() : TrainingRepository {

    private val collection = MutableStateFlow(sampleTrainings)
    private val activeId = MutableStateFlow<String?>(null)

    override fun getByPlan(planId: String): Flow<List<Training>> =
        collection.map { list ->
            list.filter { it.planId == planId }
        }

    override fun getActive(): Flow<Training?> =
        combine(collection, activeId) { collection, activeId ->
            activeId?.let { collection.findEntity(it) }
        }

    override fun getPrevious(): Flow<Training?> =
        combine(collection, activeId) { collection, activeId ->
            val activeIndex = collection.indexOfFirst { it.id == activeId }
            collection.getOrNull(activeIndex - 1)
        }

    override suspend fun setActive(trainingId: String?) {
        activeId.emit(trainingId)
    }

    override suspend fun save(training: Training) {
        collection.update { it.upsertEntity(training) }
    }
}
