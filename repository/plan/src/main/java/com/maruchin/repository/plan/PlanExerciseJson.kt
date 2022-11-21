package com.maruchin.repository.plan

import com.maruchin.core.utils.Id
import com.maruchin.model.plan.PlanExercise
import kotlinx.serialization.Serializable

@Serializable
internal data class PlanExerciseJson(
    val id: String,
    val name: String,
    val numOfSets: Int,
    val minReps: Int,
    val maxReps: Int,
)

internal fun PlanExerciseJson.asModel() = PlanExercise(
    id = Id(id),
    name = name,
    numOfSets = numOfSets,
    repsRange = minReps..maxReps
)
