package com.maruchin.model.training

import com.maruchin.core.utils.*

data class Training(
    override val id: Id = Id.generate(),
    val planId: Id,
    val planTrainingId: Id,
    val weekNumber: Int,
    val exercises: List<TrainingExercise>,
    val activeExerciseId: Id = Id.empty,
    val activeSetId: Id = Id.empty,
) : Entity {

    val activeExercisePosition: Int
        get() = exercises.entityIndex(activeExerciseId)

    val activeExercise: TrainingExercise?
        get() = exercises.findEntity(activeExerciseId)

    val activeSet: TrainingSet?
        get() = activeExercise?.sets?.findEntity(activeSetId)

    val canGoNext: Boolean
        get() = nextExercisePosition in exercises.indices

    val canGoPrevious: Boolean
        get() = previousExercisePosition in exercises.indices

    private val nextExercisePosition: Int
        get() = activeExercisePosition + 1

    private val previousExercisePosition: Int
        get() = activeExercisePosition - 1

    fun begin() = copy(
        activeExerciseId = exercises.first().id,
        activeSetId = Id.empty,
    )

    fun getSet(otherTraining: Training): TrainingSet? =
        exercises
            .find { it.number == (otherTraining.activeExercise?.number ?: -1) }
            ?.sets
            ?.find { it.number == (otherTraining.activeSet?.number ?: -1) }

    fun activateSet(setId: Id) = copy(
        activeSetId = setId
    )

    fun completeActiveSet(weight: Float, reps: Int) = copy(
        exercises = exercises.updateEntity(activeExerciseId) { exercise ->
            exercise.completeSet(activeSetId, weight, reps)
        },
        activeSetId = Id.empty,
    )

    fun activateExercise(exerciseId: Id) = copy(
        activeExerciseId = exerciseId,
        activeSetId = Id.empty
    )

    fun goNext() = activateExercise(exercises[nextExercisePosition].id)

    fun goPrevious() = activateExercise(exercises[previousExercisePosition].id)
}
