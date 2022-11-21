package com.maruchin.ui.home

import com.maruchin.core.utils.Id
import com.maruchin.model.plan.Plan
import com.maruchin.model.training.Training
import com.maruchin.model.training.TrainingExercise
import com.maruchin.model.training.TrainingSet

internal data class HomeUiState(
    val planName: String = "",
    val weeks: List<WeekUiState> = emptyList(),
) {

    val numOfWeeks: Int
        get() = weeks.size

    constructor(plan: Plan, trainings: List<Training>) : this(
        planName = plan.name,
        weeks = trainings.groupBy { it.weekNumber }.map { (_, trainings) ->
            WeekUiState(plan, trainings)
        },
    )
}

internal data class WeekUiState(
    val weekNumber: Int,
    val days: List<TrainingUiState>,
) {
    constructor(plan: Plan, trainings: Collection<Training>) : this(
        weekNumber = trainings.first().weekNumber,
        days = trainings.map { TrainingUiState(plan, it) }
    )
}

internal data class TrainingUiState(
    val trainingId: Id,
    val trainingName: String,
    val exercises: List<ExerciseUiState>,
) {
    constructor(plan: Plan, training: Training) : this(
        trainingId = training.id,
        trainingName = plan.getTraining(training.planTrainingId)?.name ?: "",
        exercises = training.exercises.map { ExerciseUiState(it) }
    )
}

internal data class ExerciseUiState(
    val exerciseName: String,
    val numOfSeries: String,
    val repsRange: String,
    val sets: List<SetUiState>,
) {
    constructor(exercise: TrainingExercise) : this(
        exerciseName = exercise.name,
        sets = exercise.sets.map { SetUiState(it) },
        numOfSeries = exercise.numOfSets.toString(),
        repsRange = "${exercise.repsRange.first} - ${exercise.repsRange.last}"
    )
}

internal data class SetUiState(
    val weight: String,
    val reps: String,
) {
    constructor(set: TrainingSet) : this(
        weight = when {
            set.completed -> "${set.weight} kg"
            else -> "--"
        },
        reps = when {
            set.completed -> set.reps.toString()
            else -> "--"
        },
    )
}
