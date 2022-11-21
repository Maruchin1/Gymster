package com.maruchin.model.training

import com.maruchin.model.plan.Plan
import com.maruchin.model.plan.PlanExercise
import javax.inject.Inject

internal class TrainingFactory @Inject constructor() {

    fun createNewWeek(plan: Plan, trainingHistory: List<Training>): List<Training> =
        plan.trainings.map { day ->
            val trainingExercises = day.exercises
                .mapIndexed { index, planExercise -> createExercise(index, planExercise) }
                .sortedBy { it.number }
            Training(
                planId = plan.id,
                planTrainingId = day.id,
                weekNumber = getNextWeekNumber(trainingHistory),
                exercises = trainingExercises,
                activeExerciseId = trainingExercises.first().id,
                activeSetId = trainingExercises.first().sets.first().id
            )
        }

    private fun getNextWeekNumber(trainingHistory: List<Training>): Int =
        (trainingHistory.maxOfOrNull { it.weekNumber } ?: 0) + 1

    private fun createExercise(index: Int, exercise: PlanExercise) = TrainingExercise(
        number = (index + 1).toString(),
        name = exercise.name,
        numOfSets = exercise.numOfSets,
        repsRange = exercise.repsRange,
        sets = (1..exercise.numOfSets).map { setNumber ->
            createSet(setNumber)
        }
    )

    private fun createSet(setNumber: Int) = TrainingSet(
        number = setNumber.toString(),
    )
}
