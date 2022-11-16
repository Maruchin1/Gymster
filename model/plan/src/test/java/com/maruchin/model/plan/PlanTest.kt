package com.maruchin.model.plan

import org.junit.jupiter.api.assertThrows
import kotlin.test.Test
import kotlin.test.assertEquals

class PlanTest {

    @Test
    fun `Get day`() {
        // Given
        val givenPlan = samplePlans[0]
        val givenDay = givenPlan.days[0]

        // When
        val day = givenPlan.getDay(givenDay.id)

        // Then
        assertEquals(day, givenDay)
    }

    @Test
    fun `Can't find day`() {
        // Given
        val givenPlan = samplePlans[0]

        // Then
        assertThrows<IllegalStateException> {
            givenPlan.getDay("999")
        }
    }
}
