package com.maruchin.repository.training.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training_exercise")
data class TrainingExerciseEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "exercise_number") val number: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "num_of_sets") val numOfSets: Int?,
    @ColumnInfo(name = "min_reps") val minReps: Int?,
    @ColumnInfo(name = "max_reps") val maxReps: Int?,
    @ColumnInfo(name = "training_id") val trainingId: String?,
)
