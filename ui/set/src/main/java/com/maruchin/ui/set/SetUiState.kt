package com.maruchin.ui.set

import com.maruchin.core.ui.formatRepsRange
import com.maruchin.model.training.Training

internal data class SetUiState(
    val setNumber: String = "",
    val exerciseName: String = "",
    val numOfSets: String = "",
    val repsRange: String = "",
    val weightAngle: Int = 0,
    val weightAngleRange: IntRange = weightRangeToAngleRange(WEIGHT_RANGE),
    val repsAngle: Int = 0,
    val repsAngleRange: IntRange = repsRangeToAngleRange(REPS_RANGE),
) {
    constructor(training: Training, previousTraining: Training?) : this(
        setNumber = training.activeSet?.id ?: "",
        exerciseName = training.activeExercise?.name ?: "",
        numOfSets = training.activeExercise?.numOfSets?.toString() ?: "",
        repsRange = training.activeExercise?.repsRange?.let { formatRepsRange(it) } ?: "",
        weightAngle = when (training.activeSet?.completed) {
            true -> training.activeSet?.weight ?: 0f
            else -> previousTraining?.getPreviousSet(training)?.weight ?: 0f
        }.let(::weightToAngle),
        repsAngle = when (training.activeSet?.completed) {
            true -> training.activeSet?.reps ?: 0
            else -> previousTraining?.getPreviousSet(training)?.reps ?: 0
        }.let(::repsToAngle),
    )

    companion object {
        private val WEIGHT_RANGE = 0..999
        private val REPS_RANGE = 1..99
    }
}
