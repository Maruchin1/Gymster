package com.maruchin.model.training

import com.maruchin.model.plan.Plan
import com.maruchin.model.plan.PlanExercise
import javax.inject.Inject

internal class TrainingFactory @Inject constructor() {

    fun createNewWeek(plan: Plan, trainingHistory: List<Training>): List<Training> =
        plan.days.map { day ->
            val trainingExercises = day.exercises
                .mapIndexed { index, planExercise -> createExercise(index, planExercise) }
                .sortedBy { it.id }
            Training(
                planId = plan.id,
                planDayId = day.id,
                weekNumber = getNextWeekNumber(trainingHistory),
                exercises = trainingExercises,
                activeExerciseId = trainingExercises.first().id,
                activeSetId = trainingExercises.first().sets.first().id
            )
        }

    private fun getNextWeekNumber(trainingHistory: List<Training>): Int =
        trainingHistory.maxOf { it.weekNumber } + 1

    private fun createExercise(index: Int, exercise: PlanExercise) = TrainingExercise(
        id = (index + 1).toString(),
        name = exercise.name,
        numOfSets = exercise.numOfSets,
        repsRange = exercise.repsRange,
        sets = (1..exercise.numOfSets).map { setNumber ->
            TrainingSet(setNumber.toString())
        }
    )
}
