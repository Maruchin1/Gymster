package com.maruchin.repository.training.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training")
data class TrainingEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "plan_id") val planId: String?,
    @ColumnInfo(name = "plan_training_id") val planTrainingId: String?,
    @ColumnInfo(name = "week_number") val weekNumber: Int?,
    @ColumnInfo(name = "active_exercise_id") val activeExerciseId: String?,
    @ColumnInfo(name = "active_set_id") val activeSetId: String?,
)
