package com.maruchin.repository.training.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TrainingDao {

    @Insert
    suspend fun add(training: TrainingEntity)

    @Insert
    suspend fun add(trainingExercise: TrainingExerciseEntity)

    @Insert
    suspend fun add(trainingSet: TrainingSetEntity)

    @Update
    suspend fun update(training: TrainingEntity)

    @Update
    suspend fun update(trainingExercise: TrainingExerciseEntity)

    @Update
    suspend fun update(trainingSet: TrainingSetEntity)

    @Delete
    suspend fun delete(training: TrainingEntity)

    @Delete
    suspend fun delete(trainingExercise: TrainingExerciseEntity)

    @Delete
    suspend fun delete(trainingSet: TrainingSetEntity)

    @Transaction
    suspend fun add(training: TrainingWithExercises) {
        add(training.training)
        training.exercises.forEach { exercise ->
            add(exercise.exercise)
            exercise.sets.forEach { set ->
                add(set)
            }
        }
    }

    @Transaction
    suspend fun update(training: TrainingWithExercises) {
        update(training.training)
        training.exercises.forEach { exercise ->
            update(exercise.exercise)
            exercise.sets.forEach { set ->
                update(set)
            }
        }
    }

    @Transaction
    suspend fun delete(training: TrainingWithExercises) {
        delete(training.training)
        training.exercises.forEach { exercise ->
            delete(exercise.exercise)
            exercise.sets.forEach { set ->
                delete(set)
            }
        }
    }

    @Transaction
    @Query("SELECT * FROM training WHERE plan_id = :planId")
    fun getByPlan(planId: String): Flow<List<TrainingWithExercises>>

    @Transaction
    @Query("SELECT * FROM training WHERE id = :id")
    fun getById(id: String): Flow<TrainingWithExercises>

    @Transaction
    @Query(
        "SELECT * FROM training WHERE plan_id = :planId " +
            "AND plan_training_id = :planTrainingId " +
            "AND week_number = :weekNumber"
    )
    fun getByWeek(
        planId: String,
        planTrainingId: String,
        weekNumber: Int
    ): Flow<List<TrainingWithExercises>>
}
