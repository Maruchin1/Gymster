package com.maruchin.model.user

import com.maruchin.core.utils.Id
import kotlin.test.Test
import kotlin.test.assertEquals

class UserTest {

    @Test
    fun `Select plan`() {
        // When
        val user = sampleUser.selectPlan(Id("1"))

        // Then
        assertEquals(Id("1"), user.preferences.activePlanId)
    }
}
