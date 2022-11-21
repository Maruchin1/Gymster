package com.maruchin.model.user

import com.maruchin.core.utils.Entity
import com.maruchin.core.utils.Id

data class User(
    override val id: Id = Id.generate(),
    val username: String,
    val preferences: UserPreferences,
) : Entity {

    fun selectPlan(planId: Id) = copy(
        preferences = preferences.copy(activePlanId = planId)
    )
}
