package com.maruchin.model.plan

import com.maruchin.core.utils.Entity
import com.maruchin.core.utils.Id

data class PlanExercise(
    override val id: Id = Id.generate(),
    val name: String,
    val numOfSets: Int,
    val repsRange: IntRange,
) : Entity
