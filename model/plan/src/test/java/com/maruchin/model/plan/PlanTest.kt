package com.maruchin.model.plan

import kotlin.test.Test
import kotlin.test.assertEquals

class PlanTest {

    @Test
    fun `Get day`() {
        // Given
        val givenTraining = samplePushPullPlan.trainings[0]

        // When
        val training = samplePushPullPlan.getTraining(givenTraining.id)

        // Then
        assertEquals(training, givenTraining)
    }
}
