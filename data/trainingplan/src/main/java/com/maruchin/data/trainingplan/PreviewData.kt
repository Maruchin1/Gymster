package com.maruchin.data.trainingplan

val previewTrainingPlans = listOf(
    TrainingPlan.newPlan(),
    TrainingPlan(
        name = "Push Pull",
        trainings = listOf(
            Training(
                name = "Push",
                exercises = listOf(
                    Exercise(
                        name = "Wyciskanie na ławce płaskiej",
                        numOfSeries = 3,
                        repsRange = 4..6
                    ),
                    Exercise(
                        name = "Rozpiętki na bramie",
                        numOfSeries = 4,
                        repsRange = 12..15,
                    )
                )
            ),
            Training(
                name = "Pull",
                exercises = listOf(
                    Exercise(
                        name = "Podciaganie na drążku",
                        numOfSeries = 3,
                        repsRange = 8..10,
                    ),
                    Exercise(
                        name = "Wiosłowanie sztangą",
                        numOfSeries = 3,
                        repsRange = 10..12,
                    )
                )
            )
        )
    )
)
