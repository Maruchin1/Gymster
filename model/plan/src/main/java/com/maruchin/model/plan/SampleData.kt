package com.maruchin.model.plan

import com.maruchin.core.utils.Id

val samplePushPullPlan = Plan(
    id = Id("1"),
    name = "Push Pull",
    trainings = listOf(
        PlanTraining(
            id = Id("1"),
            name = "Push",
            exercises = listOf(
                PlanExercise(
                    id = Id("1"),
                    name = "Barbell press on a horizontal bench",
                    numOfSets = 3,
                    repsRange = 4..6,
                ),
                PlanExercise(
                    id = Id("2"),
                    name = "Dumbbell press on an incline bench",
                    numOfSets = 3,
                    repsRange = 6..8,
                )
            )
        ),
        PlanTraining(
            id = Id("2"),
            name = "Pull",
            exercises = listOf(
                PlanExercise(
                    id = Id("3"),
                    name = "Rowing a barbell with an overgrip",
                    numOfSets = 3,
                    repsRange = 4..6
                ),
                PlanExercise(
                    id = Id("4"),
                    name = "Pulling the bar on the lift",
                    numOfSets = 3,
                    repsRange = 6..8
                )
            )
        )
    )
)
