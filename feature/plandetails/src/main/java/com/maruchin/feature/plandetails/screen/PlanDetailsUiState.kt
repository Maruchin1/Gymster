package com.maruchin.feature.plandetails.screen

import com.maruchin.data.trainingplan.Exercise
import com.maruchin.data.trainingplan.Training
import com.maruchin.data.trainingplan.TrainingPlan

internal data class PlanDetailsUiState(
    val planId: String = "",
    val name: String = "",
    val trainings: List<TrainingUiState> = emptyList(),
) {
    constructor(plan: TrainingPlan) : this(
        planId = plan.id,
        name = plan.name,
        trainings = plan.trainings.map { TrainingUiState(it) },
    )
}

internal data class TrainingUiState(
    val trainingId: String = "",
    val name: String = "",
    val exercises: List<ExerciseUiState> = emptyList(),
) {
    constructor(training: Training) : this(
        trainingId = training.id,
        name = training.name,
        exercises = training.exercises.map { ExerciseUiState(it) }
    )
}

internal data class ExerciseUiState(
    val exerciseId: String = "",
    val name: String = "",
    val numOfSeries: String = "",
    val minReps: String = "",
    val maxReps: String = "",
) {
    constructor(exercise: Exercise) : this(
        exerciseId = exercise.id,
        name = exercise.name,
        numOfSeries = exercise.numOfSeries.toString(),
        minReps = exercise.repsRange.first.toString(),
        maxReps = exercise.repsRange.last.toString(),
    )
}
