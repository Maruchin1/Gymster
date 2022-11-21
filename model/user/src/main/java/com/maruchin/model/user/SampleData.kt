package com.maruchin.model.user

import com.maruchin.core.utils.Id

val sampleUser = User(
    id = Id("1"),
    username = "John Doe",
    preferences = UserPreferences(
        activePlanId = Id("1")
    )
)
