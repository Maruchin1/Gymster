package com.maruchin.model.plan

import com.maruchin.core.utils.*

data class Plan(
    override val id: Id = Id.generate(),
    val name: String,
    val trainings: List<PlanTraining>,
) : Entity {

    fun getTraining(id: Id): PlanTraining? =
        trainings.findEntity(id)
}
