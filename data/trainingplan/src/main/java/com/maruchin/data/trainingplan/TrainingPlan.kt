package com.maruchin.data.trainingplan

import com.maruchin.core.utils.*

data class TrainingPlan(
    override val id: String = generateId(),
    val name: String,
    val trainings: List<Training>,
) : Entity {

    fun modify(newName: String) = copy(
        name = newName
    )

    fun addTraining() = copy(
        trainings = trainings.addEntity(Training.placeholder(trainings.size + 1))
    )

    fun modifyTraining(trainingId: String, newName: String) = copy(
        trainings = trainings.updateEntity(trainingId) { training ->
            training.copy(name = newName)
        }
    )

    fun removeTraining(trainingId: String) = copy(
        trainings = trainings.removeEntity(trainingId)
    )

    fun addExercise(trainingId: String) = copy(
        trainings = trainings.updateEntity(trainingId) { training ->
            training.copy(
                exercises = training.exercises.addEntity(Exercise.placeholder())
            )
        }
    )

    fun modifyExercise(
        trainingId: String,
        exerciseId: String,
        newName: String,
        newNumOfSeries: Int,
        newRepsRange: IntRange,
    ) = copy(
        trainings = trainings.updateEntity(trainingId) { training ->
            training.copy(
                exercises = training.exercises.updateEntity(exerciseId) { exercise ->
                    exercise.copy(
                        name = newName,
                        numOfSeries = newNumOfSeries,
                        repsRange = newRepsRange,
                    )
                }
            )
        }
    )

    fun removeExercise(trainingId: String, exerciseId: String) = copy(
        trainings = trainings.updateEntity(trainingId) { training ->
            training.copy(
                exercises = training.exercises.removeEntity(exerciseId)
            )
        }
    )

    companion object {

        fun newPlan() = TrainingPlan(
            name = "Mój plan treningowy",
            trainings = listOf(Training.placeholder(1), Training.placeholder(2))
        )
    }
}
