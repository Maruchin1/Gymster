package com.maruchin.repository.plan

import com.maruchin.core.utils.Id
import com.maruchin.model.plan.Plan
import kotlinx.serialization.Serializable

@Serializable
internal data class PlanJson(
    val id: String,
    val name: String,
    val trainings: List<PlanTrainingJson>,
)

internal fun PlanJson.asModel() = Plan(
    id = Id(id),
    name = name,
    trainings = trainings.map { it.asModel() }
)
