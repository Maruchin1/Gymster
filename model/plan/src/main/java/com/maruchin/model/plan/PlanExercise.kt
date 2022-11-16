package com.maruchin.model.plan

import com.maruchin.core.utils.Entity
import com.maruchin.core.utils.generateId

data class PlanExercise(
    override val id: String = generateId(),
    val name: String,
    val numOfSets: Int,
    val repsRange: IntRange,
) : Entity
