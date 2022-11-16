package com.maruchin.ui.training

import com.maruchin.core.ui.formatRepsRange
import com.maruchin.core.ui.formatWeight
import com.maruchin.core.utils.findEntity
import com.maruchin.model.plan.Plan
import com.maruchin.model.training.TrainingExercise
import com.maruchin.model.training.Training
import com.maruchin.model.training.TrainingSet

data class TrainingUiState(
    val trainingId: String = "",
    val planName: String = "",
    val planDayName: String = "",
    val exercises: List<ExerciseUiState> = emptyList(),
    val currentPosition: Int = 0,
    val canGoPrevious: Boolean = false,
    val canGoNext: Boolean = false,
) {
    constructor(plan: Plan, training: Training) : this(
        trainingId = training.id,
        planName = plan.name,
        planDayName = plan.days.findEntity(training.planDayId)?.name ?: "",
        exercises = training.exercises.map { ExerciseUiState(training, it) },
        currentPosition = training.activeExercisePosition,
        canGoPrevious = training.canGoPrevious,
        canGoNext = training.canGoNext,
    )
}

data class ExerciseUiState(
    val id: String,
    val name: String = "",
    val numOfSeries: String = "",
    val repsRange: String = "",
    val sets: List<SetUiState> = emptyList(),
    val completed: Boolean = false,
    val active: Boolean = false,
) {
    constructor(training: Training, exercise: TrainingExercise) : this(
        id = exercise.id,
        name = exercise.name,
        numOfSeries = exercise.numOfSets.toString(),
        repsRange = formatRepsRange(exercise.repsRange),
        sets = exercise.sets.map { SetUiState(it) },
        completed = exercise.completed,
        active = training.activeExercise == exercise,
    )
}

data class SetUiState(
    val id: String,
    val reps: String,
    val weight: String,
) {
    constructor(trainingSet: TrainingSet) : this(
        id = trainingSet.id,
        reps = when {
            trainingSet.completed -> trainingSet.reps.toString()
            else -> "--"
        },
        weight = when {
            trainingSet.completed -> formatWeight(trainingSet.weight)
            else -> "--"
        }
    )
}
