package com.maruchin.model.user

import kotlin.test.Test
import kotlin.test.assertEquals

class UserTest {

    @Test
    fun `Select plan`() {
        // When
        val user = sampleUser.selectPlan("p2")

        // Then
        assertEquals("p2", user.preferences.activePlanId)
    }
}
