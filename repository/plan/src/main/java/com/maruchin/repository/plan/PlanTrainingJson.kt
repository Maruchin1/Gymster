package com.maruchin.repository.plan

import com.maruchin.core.utils.Id
import com.maruchin.model.plan.PlanTraining
import kotlinx.serialization.Serializable

@Serializable
internal data class PlanTrainingJson(
    val id: String,
    val name: String,
    val exercises: List<PlanExerciseJson>,
)

internal fun PlanTrainingJson.asModel() = PlanTraining(
    id = Id(id),
    name = name,
    exercises = exercises.map { it.asModel() }
)
