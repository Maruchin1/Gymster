package com.maruchin.model.training

import kotlin.test.*

class TrainingTest {

    @Test
    fun `Started training`() {
        // When
        val training = sampleTrainings[0]

        // Then
        training.run {
            assertTrue(canGoNext)
            assertFalse(canGoPrevious)
            assertEquals(training.exercises[0], activeExercise)
            assertEquals(training.exercises[0].sets[0], activeSet)
        }
    }

    @Test
    fun `Move forward`() {
        // When
        val training = sampleTrainings[0]
            .goToNextExercise()
            .goToNextExercise()

        // Then
        training.run {
            assertTrue(canGoNext)
            assertTrue(canGoPrevious)
            assertEquals(training.exercises[2], activeExercise)
            assertEquals(training.exercises[2].sets[0], activeSet)
        }
    }

    @Test
    fun `Move forward and backward`() {
        // When
        val training = sampleTrainings[0]
            .goToNextExercise()
            .goToNextExercise()
            .goToPreviousExercise()

        // Then
        training.run {
            assertTrue(canGoNext)
            assertTrue(canGoPrevious)
            assertEquals(training.exercises[1], activeExercise)
            assertEquals(training.exercises[1].sets[0], activeSet)
        }
    }

    @Test
    fun `Activate specific exercise`() {
        // When
        val training = sampleTrainings[0]
            .activateExercise(sampleTrainings[0].exercises[3].id)

        // Then
        training.run {
            assertTrue(canGoNext)
            assertTrue(canGoPrevious)
            assertEquals(training.exercises[3], activeExercise)
            assertEquals(training.exercises[3].sets[0], activeSet)
        }
    }

    @Test
    fun `Activate last exercise`() {
        // When
        val training = sampleTrainings[0]
            .activateExercise(sampleTrainings[0].exercises[5].id)

        // Then
        training.run {
            assertFalse(canGoNext)
            assertTrue(canGoPrevious)
            assertEquals(training.exercises[5], activeExercise)
            assertEquals(training.exercises[5].sets[0], activeSet)
        }
    }

    @Test
    fun `Activate set`() {
        // When
        val training = sampleTrainings[0]
            .activateSet(sampleTrainings[0].exercises[0].sets[1].id)

        // Then
        training.run {
            assertEquals(training.exercises[0].sets[1], activeSet)
        }
    }

    @Test
    fun `Complete active set`() {
        // When
        val training = sampleTrainings[0]
            .completeActiveSet(weight = 60f, reps = 8)

        // Then
        training.run {
            assertEquals(TrainingSet(id = "1", weight = 60f, reps = 8), activeSet)
        }
    }

    @Test
    fun `Get last set`() {
        // When
        val training = sampleTrainings[2]
            .goToNextExercise()
            .activateSet(sampleTrainings[2].exercises[1].sets[1].id)
        val lastSet = sampleTrainings[0]
            .getPreviousSet(training)

        // Then
        assertEquals(sampleTrainings[0].exercises[1].sets[1], lastSet)
    }
}
