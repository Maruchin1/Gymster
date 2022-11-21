package com.maruchin.repository.plan

import com.maruchin.core.utils.ApplicationScope
import com.maruchin.core.utils.Id
import com.maruchin.core.utils.findEntity
import com.maruchin.model.plan.Plan
import com.maruchin.model.plan.PlanRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class CachingPlanRepository @Inject constructor(
    private val localDataSource: PlanLocalDataSource,
    @ApplicationScope private val scope: CoroutineScope,
) : PlanRepository {

    private val collectionCache = MutableStateFlow(emptyList<Plan>())

    override fun getAll(): Flow<List<Plan>> =
        collectionCache
            .onStart { loadLocalPlansIfNotCached() }

    override fun getById(planId: Id): Flow<Plan?> =
        collectionCache
            .onStart { loadLocalPlansIfNotCached() }
            .map { it.findEntity(planId) }

    private fun loadLocalPlansIfNotCached() {
        if (collectionCache.value.isEmpty()) loadLocalPlans()
    }

    private fun loadLocalPlans() = scope.launch {
        localDataSource.getAll().onSuccess { localPlans ->
            collectionCache.emit(localPlans)
        }
    }
}
