package com.maruchin.model.training

val sampleTrainings = listOf(
    Training(
        id = "1",
        planId = "1",
        planDayId = "1",
        weekNumber = 1,
        exercises = listOf(
            TrainingExercise(
                id = "1",
                name = "Barbell press on a horizontal bench",
                numOfSets = 3,
                repsRange = 4..6,
                sets = listOf(
                    TrainingSet(id = "1", weight = 90f, reps = 6),
                    TrainingSet(id = "2", weight = 90f, reps = 6),
                    TrainingSet(id = "3", weight = 90f, reps = 6),
                )
            ),
            TrainingExercise(
                id = "2",
                name = "Dumbbell press on an incline bench",
                numOfSets = 3,
                repsRange = 6..8,
                sets = listOf(
                    TrainingSet(id = "1", weight = 30f, reps = 6),
                    TrainingSet(id = "2", weight = 30f, reps = 6),
                    TrainingSet(id = "3", weight = 30f, reps = 6),
                )
            ),
            TrainingExercise(
                id = "3",
                name = "Dumbbell fly",
                numOfSets = 3,
                repsRange = 12..15,
                sets = listOf(
                    TrainingSet(id = "1", weight = 35f, reps = 15),
                    TrainingSet(id = "2", weight = 35f, reps = 15),
                    TrainingSet(id = "3", weight = 35f, reps = 15),
                )
            ),
            TrainingExercise(
                id = "4",
                name = "Straightening on the lift with one hand",
                numOfSets = 3,
                repsRange = 12..15,
                sets = listOf(
                    TrainingSet(id = "1", weight = 10f, reps = 15),
                    TrainingSet(id = "2", weight = 10f, reps = 15),
                    TrainingSet(id = "3", weight = 10f, reps = 15),
                )
            ),
            TrainingExercise(
                id = "5",
                name = "Sideways rises on a one-handed lift",
                numOfSets = 3,
                repsRange = 12..15,
                sets = listOf(
                    TrainingSet(id = "1", weight = 5f, reps = 15),
                    TrainingSet(id = "2", weight = 5f, reps = 15),
                    TrainingSet(id = "3", weight = 5f, reps = 15),
                )
            ),
            TrainingExercise(
                id = "6",
                name = "Squat with a dumbbell",
                numOfSets = 2,
                repsRange = 10..12,
                sets = listOf(
                    TrainingSet(id = "1", weight = 50f, reps = 12),
                    TrainingSet(id = "2", weight = 50f, reps = 12),
                )
            )
        ),
        activeExerciseId = "1",
        activeSetId = "1",
    ),
    Training(
        id = "2",
        planId = "1",
        planDayId = "2",
        weekNumber = 1,
        exercises = listOf(
            TrainingExercise(
                id = "1",
                name = "Rowing a barbell with an overgrip",
                numOfSets = 3,
                repsRange = 4..6,
                sets = listOf(
                    TrainingSet(id = "1", weight = 60f, reps = 6),
                    TrainingSet(id = "2", weight = 60f, reps = 6),
                    TrainingSet(id = "3", weight = 60f, reps = 6),
                )
            ),
            TrainingExercise(
                id = "2",
                name = "Pulling the bar on the lift",
                numOfSets = 3,
                repsRange = 6..8,
                sets = listOf(
                    TrainingSet(id = "1", weight = 50f, reps = 8),
                    TrainingSet(id = "2", weight = 50f, reps = 8),
                    TrainingSet(id = "3", weight = 50f, reps = 8),
                )
            ),
            TrainingExercise(
                id = "3",
                name = "One-handed rowing on the lift",
                numOfSets = 3,
                repsRange = 12..15,
                sets = listOf(
                    TrainingSet(id = "1", weight = 20f, reps = 15),
                    TrainingSet(id = "2", weight = 20f, reps = 15),
                    TrainingSet(id = "3", weight = 20f, reps = 15),
                )
            ),
            TrainingExercise(
                id = "4",
                name = "Romanian dumbbell deadlift",
                numOfSets = 2,
                repsRange = 10..12,
                sets = listOf(
                    TrainingSet(id = "1", weight = 35f, reps = 12),
                    TrainingSet(id = "2", weight = 35f, reps = 12),
                )
            ),
            TrainingExercise(
                id = "5",
                name = "Dumbbell bending while standing",
                numOfSets = 3,
                repsRange = 12..15,
                sets = listOf(
                    TrainingSet(id = "1", weight = 10f, reps = 15),
                    TrainingSet(id = "2", weight = 10f, reps = 15),
                    TrainingSet(id = "3", weight = 10f, reps = 15),
                )
            ),
            TrainingExercise(
                id = "6",
                name = "Reverse flaps on the gate",
                numOfSets = 3,
                repsRange = 12..15,
                sets = listOf(
                    TrainingSet(id = "1", weight = 25f, reps = 15),
                    TrainingSet(id = "2", weight = 25f, reps = 15),
                    TrainingSet(id = "3", weight = 25f, reps = 15),
                )
            )
        ),
        activeExerciseId = "1",
        activeSetId = "1"
    ),
    Training(
        id = "3",
        planId = "1",
        planDayId = "1",
        weekNumber = 2,
        exercises = listOf(
            TrainingExercise(
                id = "1",
                name = "Barbell press on a horizontal bench",
                numOfSets = 3,
                repsRange = 4..6,
                sets = listOf(
                    TrainingSet("1"),
                    TrainingSet("2"),
                    TrainingSet("3"),
                )
            ),
            TrainingExercise(
                id = "2",
                name = "Dumbbell press on an incline bench",
                numOfSets = 3,
                repsRange = 6..8,
                sets = listOf(
                    TrainingSet("1"),
                    TrainingSet("2"),
                    TrainingSet("3"),
                )
            ),
            TrainingExercise(
                id = "3",
                name = "Dumbbell fly",
                numOfSets = 3,
                repsRange = 12..15,
                sets = listOf(
                    TrainingSet("1"),
                    TrainingSet("2"),
                    TrainingSet("3"),
                )
            ),
            TrainingExercise(
                id = "4",
                name = "Straightening on the lift with one hand",
                numOfSets = 3,
                repsRange = 12..15,
                sets = listOf(
                    TrainingSet("1"),
                    TrainingSet("2"),
                    TrainingSet("3"),
                )
            ),
            TrainingExercise(
                id = "5",
                name = "Sideways rises on a one-handed lift",
                numOfSets = 3,
                repsRange = 12..15,
                sets = listOf(
                    TrainingSet("1"),
                    TrainingSet("2"),
                    TrainingSet("3"),
                )
            ),
            TrainingExercise(
                id = "6",
                name = "Squat with a dumbbell",
                numOfSets = 2,
                repsRange = 10..12,
                sets = listOf(
                    TrainingSet("1"),
                    TrainingSet("2"),
                )
            )
        ),
        activeExerciseId = "1",
        activeSetId = "1"
    ),
    Training(
        id = "4",
        planId = "1",
        planDayId = "2",
        weekNumber = 2,
        exercises = listOf(
            TrainingExercise(
                id = "1",
                name = "Rowing a barbell with an overgrip",
                numOfSets = 3,
                repsRange = 4..6,
                sets = listOf(
                    TrainingSet("1"),
                    TrainingSet("2"),
                    TrainingSet("3"),
                )
            ),
            TrainingExercise(
                id = "2",
                name = "Pulling the bar on the lift",
                numOfSets = 3,
                repsRange = 6..8,
                sets = listOf(
                    TrainingSet("1"),
                    TrainingSet("2"),
                    TrainingSet("3"),
                )
            ),
            TrainingExercise(
                id = "3",
                name = "One-handed rowing on the lift",
                numOfSets = 3,
                repsRange = 12..15,
                sets = listOf(
                    TrainingSet("1"),
                    TrainingSet("2"),
                    TrainingSet("3"),
                )
            ),
            TrainingExercise(
                id = "4",
                name = "Romanian dumbbell deadlift",
                numOfSets = 2,
                repsRange = 10..12,
                sets = listOf(
                    TrainingSet("1"),
                    TrainingSet("2"),
                )
            ),
            TrainingExercise(
                id = "5",
                name = "Dumbbell bending while standing",
                numOfSets = 3,
                repsRange = 12..15,
                sets = listOf(
                    TrainingSet("1"),
                    TrainingSet("2"),
                    TrainingSet("3"),
                )
            ),
            TrainingExercise(
                id = "6",
                name = "Reverse flaps on the gate",
                numOfSets = 3,
                repsRange = 12..15,
                sets = listOf(
                    TrainingSet("1"),
                    TrainingSet("2"),
                    TrainingSet("3"),
                )
            )
        ),
        activeExerciseId = "1",
        activeSetId = "1"
    ),
)
