package com.maruchin.model.training

import com.maruchin.core.utils.*

data class Training(
    override val id: String = generateId(),
    val planId: String,
    val planDayId: String,
    val weekNumber: Int,
    val exercises: List<TrainingExercise>,
    private val activeExerciseId: String = "",
    private val activeSetId: String = "",
) : Entity {

    val canGoNext: Boolean
        get() = nextExercisePosition in exercises.indices

    val canGoPrevious: Boolean
        get() = previousExercisePosition in exercises.indices

    val activeExercise: TrainingExercise?
        get() = exercises.findEntity(activeExerciseId)

    val activeSet: TrainingSet?
        get() = activeExercise?.sets?.findEntity(activeSetId)

    val activeExercisePosition: Int
        get() = exercises.indexOfFirst { it.id == activeExerciseId }

    private val nextExercisePosition: Int
        get() = activeExercisePosition + 1

    private val previousExercisePosition: Int
        get() = activeExercisePosition - 1

    fun getPreviousSet(activeTraining: Training): TrainingSet? {
        val exercise = activeTraining.activeExercise
        val set = activeTraining.activeSet
        return if (exercise != null && set != null) {
            exercises.findEntity(exercise.id)?.sets?.findEntity(set.id)
        } else null
    }

    fun activateExercise(exerciseId: String) = copy(activeExerciseId = exerciseId)

    fun activateSet(setId: String) = copy(activeSetId = setId)

    fun goToNextExercise() = activateExercise(exercises[nextExercisePosition].id)

    fun goToPreviousExercise() = activateExercise(exercises[previousExercisePosition].id)

    fun completeActiveSet(weight: Float, reps: Int) = copy(
        exercises = exercises.updateEntity(activeExerciseId) { exercise ->
            exercise.completeSet(activeSetId, weight, reps)
        }
    )
}
