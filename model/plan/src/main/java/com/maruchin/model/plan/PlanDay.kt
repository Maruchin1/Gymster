package com.maruchin.model.plan

import com.maruchin.core.utils.Entity
import com.maruchin.core.utils.generateId

data class PlanDay(
    override val id: String = generateId(),
    val name: String,
    val exercises: List<PlanExercise>,
) : Entity
