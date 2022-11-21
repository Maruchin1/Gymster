package com.maruchin.model.training

import com.maruchin.core.utils.Id

val samplePushTraining = Training(
    id = Id("1"),
    planId = Id("1"),
    planTrainingId = Id("1"),
    weekNumber = 1,
    exercises = listOf(
        TrainingExercise(
            id = Id("1"),
            number = "1",
            name = "Barbell press on a horizontal bench",
            numOfSets = 3,
            repsRange = 4..6,
            sets = listOf(
                TrainingSet(id = Id("1"), number = "1"),
                TrainingSet(id = Id("2"), number = "2"),
                TrainingSet(id = Id("3"), number = "3"),
            )
        ),
        TrainingExercise(
            id = Id("2"),
            number = "2",
            name = "Dumbbell press on an incline bench",
            numOfSets = 3,
            repsRange = 6..8,
            sets = listOf(
                TrainingSet(id = Id("4"), number = "1"),
                TrainingSet(id = Id("5"), number = "2"),
                TrainingSet(id = Id("6"), number = "3"),
            )
        )
    ),
)

val samplePullTraining = Training(
    id = Id("2"),
    planId = Id("1"),
    planTrainingId = Id("2"),
    weekNumber = 1,
    exercises = listOf(
        TrainingExercise(
            id = Id("3"),
            number = "1",
            name = "Rowing a barbell with an overgrip",
            numOfSets = 3,
            repsRange = 4..6,
            sets = listOf(
                TrainingSet(id = Id("1"), number = "1"),
                TrainingSet(id = Id("2"), number = "2"),
                TrainingSet(id = Id("3"), number = "3"),
            )
        ),
        TrainingExercise(
            id = Id("3"),
            number = "2",
            name = "Pulling the bar on the lift",
            numOfSets = 3,
            repsRange = 6..8,
            sets = listOf(
                TrainingSet(id = Id("1"), number = "1"),
                TrainingSet(id = Id("2"), number = "2"),
                TrainingSet(id = Id("3"), number = "3"),
            )
        )
    )
)

val sampleNextPushTraining = samplePushTraining.copy(id = Id("3"), weekNumber = 2)

val sampleNextPullTraining = samplePullTraining.copy(id = Id("4"), weekNumber = 2)

val sampleTrainingHistory = listOf(samplePushTraining, samplePullTraining)

val sampleNewWeek = listOf(sampleNextPushTraining, sampleNextPullTraining)
