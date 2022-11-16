package com.maruchin.model.plan

import com.maruchin.core.utils.*

data class Plan(
    override val id: String = generateId(),
    val name: String,
    val days: List<PlanDay>,
) : Entity {

    fun getDay(id: String): PlanDay =
        days.findEntity(id).let(::checkNotNull)
}
