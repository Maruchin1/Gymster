package com.maruchin.repository.plan

import android.content.res.Resources
import com.maruchin.core.utils.IoDispatcher
import com.maruchin.model.plan.Plan
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

internal class PlanLocalDataSource @Inject constructor(
    private val resources: Resources,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun getAll(): Result<List<Plan>> = withContext(dispatcher) {
        runCatching {
            resources.openRawResource(R.raw.plans).let { stream ->
                Json.decodeFromStream<List<PlanJson>>(stream).map { it.asModel() }
            }
        }
    }
}
