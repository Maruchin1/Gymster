package com.maruchin.model.user

import com.maruchin.core.utils.Entity
import com.maruchin.core.utils.generateId

data class User(
    override val id: String = generateId(),
    val username: String,
    val preferences: UserPreferences,
) : Entity {

    fun selectPlan(planId: String) = copy(
        preferences = preferences.copy(
            activePlanId = planId
        )
    )
}
