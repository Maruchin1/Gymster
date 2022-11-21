package com.maruchin.model.plan

import com.maruchin.core.utils.Entity
import com.maruchin.core.utils.Id

data class PlanTraining(
    override val id: Id = Id.generate(),
    val name: String,
    val exercises: List<PlanExercise>,
) : Entity
