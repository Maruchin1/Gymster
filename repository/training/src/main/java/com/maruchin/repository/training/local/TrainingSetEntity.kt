package com.maruchin.repository.training.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.maruchin.core.utils.Id
import com.maruchin.model.training.TrainingSet

@Entity(tableName = "training_set")
data class TrainingSetEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "set_number") val setNumber: String?,
    @ColumnInfo(name = "weight") val weight: Float?,
    @ColumnInfo(name = "reps") val reps: Int?,
    @ColumnInfo(name = "training_exercise_id") val trainingExerciseId: String?,
)

internal fun TrainingSetEntity.asModel() = TrainingSet(
    id = Id(id),
    number = setNumber ?: "",
    weight = weight ?: 0f,
    reps = reps ?: 0,
)

internal fun TrainingSet.asDb(trainingExerciseId: Id) = TrainingSetEntity(
    id = id.value,
    setNumber = number,
    weight = weight,
    reps = reps,
    trainingExerciseId = trainingExerciseId.value
)
