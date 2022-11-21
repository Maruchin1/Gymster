package com.maruchin.model.training

import kotlin.test.*

class TrainingTest {

    @Test
    fun `Begin training`() {
        // When
        val training = samplePushTraining.begin()

        // Then
        training.run {
            assertTrue(canGoNext)
            assertFalse(canGoPrevious)
            assertEquals(exercises.first(), activeExercise)
            assertNull(activeSet)
        }
    }

    @Test
    fun `Get set`() {
        // Given
        val activeTraining = sampleNextPushTraining
            .begin()
            .activateSet(sampleNextPushTraining.exercises.first().sets.first().id)

        // When
        val set = samplePushTraining
            .begin()
            .getSet(activeTraining)

        // Then
        assertEquals(samplePushTraining.exercises.first().sets.first(), set)
    }

    @Test
    fun `Activate set`() {
        // When
        val training = samplePushTraining
            .begin()
            .activateSet(samplePushTraining.exercises.first().sets[1].id)

        // Then
        training.run {
            assertEquals(training.exercises.first().sets[1], activeSet)
        }
    }

    @Test
    fun `Complete active set`() {
        // When
        val training = samplePushTraining
            .begin()
            .activateSet(samplePushTraining.exercises.first().sets[1].id)
            .completeActiveSet(weight = 60f, reps = 8)

        // Then
        training.run {
            assertNull(activeSet)
            exercises.first().sets[1].run {
                assertEquals(60f, weight)
                assertEquals(8, reps)
            }
        }
    }

    @Test
    fun `Move forward`() {
        // When
        val training = samplePushTraining
            .begin()
            .activateSet(samplePushTraining.exercises.first().sets.first().id)
            .goNext()

        // Then
        training.run {
            assertFalse(canGoNext)
            assertTrue(canGoPrevious)
            assertEquals(exercises.last(), activeExercise)
            assertNull(activeSet)
        }
    }

    @Test
    fun `Move forward and backward`() {
        // When
        val training = samplePushTraining
            .begin()
            .goNext()
            .activateSet(samplePushTraining.exercises.last().sets.first().id)
            .goPrevious()

        // Then
        training.run {
            assertTrue(canGoNext)
            assertFalse(canGoPrevious)
            assertEquals(exercises.first(), activeExercise)
            assertNull(activeSet)
        }
    }

    @Test
    fun `Activate specific exercise`() {
        // When
        val training = samplePushTraining
            .begin()
            .activateExercise(samplePushTraining.exercises.last().id)

        // Then
        training.run {
            assertFalse(canGoNext)
            assertTrue(canGoPrevious)
            assertEquals(exercises.last(), activeExercise)
            assertNull(activeSet)
        }
    }
}
