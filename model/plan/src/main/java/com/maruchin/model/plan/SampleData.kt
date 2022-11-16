package com.maruchin.model.plan

val samplePlans = listOf(
    Plan(
        id = "1",
        name = "PUSH PULL",
        days = listOf(
            PlanDay(
                id = "1",
                name = "PUSH STRENGTH",
                exercises = listOf(
                    PlanExercise(
                        id = "1",
                        name = "Barbell press on a horizontal bench",
                        numOfSets = 3,
                        repsRange = 4..6
                    ),
                    PlanExercise(
                        id = "2",
                        name = "Dumbbell press on an incline bench",
                        numOfSets = 3,
                        repsRange = 6..8,
                    ),
                    PlanExercise(
                        id = "3",
                        name = "Dumbbell fly",
                        numOfSets = 3,
                        repsRange = 12..15,
                    ),
                    PlanExercise(
                        id = "4",
                        name = "Straightening on the lift with one hand",
                        numOfSets = 3,
                        repsRange = 12..15,
                    ),
                    PlanExercise(
                        id = "5",
                        name = "Sideways rises on a one-handed lift",
                        numOfSets = 3,
                        repsRange = 12..15,
                    ),
                    PlanExercise(
                        id = "6",
                        name = "Squat with a dumbbell",
                        numOfSets = 2,
                        repsRange = 10..12,
                    )
                )
            ),
            PlanDay(
                id = "2",
                name = "Pull",
                exercises = listOf(
                    PlanExercise(
                        id = "1",
                        name = "Rowing a barbell with an overgrip",
                        numOfSets = 3,
                        repsRange = 4..6,
                    ),
                    PlanExercise(
                        id = "2",
                        name = "Pulling the bar on the lift",
                        numOfSets = 3,
                        repsRange = 6..8,
                    ),
                    PlanExercise(
                        id = "3",
                        name = "One-handed rowing on the lift",
                        numOfSets = 3,
                        repsRange = 12..15
                    ),
                    PlanExercise(
                        id = "4",
                        name = "Romanian dumbbell deadlift",
                        numOfSets = 2,
                        repsRange = 10..12,
                    ),
                    PlanExercise(
                        id = "5",
                        name = "Dumbbell bending while standing",
                        numOfSets = 3,
                        repsRange = 12..15,
                    ),
                    PlanExercise(
                        id = "6",
                        name = "Reverse flaps on the gate",
                        numOfSets = 3,
                        repsRange = 12..15
                    )
                )
            )
        )
    )
)
