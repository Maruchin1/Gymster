package com.maruchin.model.training

import com.maruchin.core.utils.generateId
import com.maruchin.model.plan.samplePlans
import io.mockk.every
import io.mockk.mockkStatic
import kotlin.test.Test
import kotlin.test.assertContentEquals

class TrainingFactoryTest {

    private val trainingFactory = TrainingFactory()

    @Test
    fun `Create new week`() {
        // Given
        mockkStatic(::generateId)
        every { generateId() } returns "5" andThen "6"

        // When
        val newWeek = trainingFactory.createNewWeek(samplePlans[0], sampleTrainings)

        // Then
        assertContentEquals(
            newWeek,
            listOf(
                sampleTrainings[2].copy(id = "5", weekNumber = 3),
                sampleTrainings[3].copy(id = "6", weekNumber = 3),
            )
        )
    }
}
