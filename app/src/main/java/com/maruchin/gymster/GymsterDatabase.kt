package com.maruchin.gymster

import androidx.room.Database
import androidx.room.RoomDatabase
import com.maruchin.repository.training.local.TrainingDao
import com.maruchin.repository.training.local.TrainingEntity
import com.maruchin.repository.training.local.TrainingExerciseEntity
import com.maruchin.repository.training.local.TrainingSetEntity

@Database(
    entities = [TrainingEntity::class, TrainingExerciseEntity::class, TrainingSetEntity::class],
    version = 1
)
internal abstract class GymsterDatabase : RoomDatabase() {
    abstract fun trainingDao(): TrainingDao
}
