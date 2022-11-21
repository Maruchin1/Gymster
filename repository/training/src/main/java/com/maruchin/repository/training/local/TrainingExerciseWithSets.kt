package com.maruchin.repository.training.local

import androidx.room.Embedded
import androidx.room.Relation
import com.maruchin.core.utils.Id
import com.maruchin.model.training.TrainingExercise

data class TrainingExerciseWithSets(
    @Embedded
    val exercise: TrainingExerciseEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "training_exercise_id",
        entity = TrainingSetEntity::class
    )
    val sets: List<TrainingSetEntity>
)

internal fun TrainingExerciseWithSets.asModel() = TrainingExercise(
    id = Id(exercise.id),
    number = exercise.number ?: "",
    name = exercise.name ?: "",
    numOfSets = exercise.numOfSets ?: 0,
    repsRange = (exercise.minReps ?: 0)..(exercise.maxReps ?: 0),
    sets = sets.map { it.asModel() }
)

internal fun TrainingExercise.asDb(trainingId: String) = TrainingExerciseWithSets(
    exercise = TrainingExerciseEntity(
        id = id.value,
        number = number,
        name = name,
        numOfSets = numOfSets,
        minReps = repsRange.first,
        maxReps = repsRange.last,
        trainingId = trainingId
    ),
    sets = sets.map { it.asDb(id) }
)
