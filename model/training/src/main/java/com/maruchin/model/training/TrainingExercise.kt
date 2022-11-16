package com.maruchin.model.training

import com.maruchin.core.utils.Entity
import com.maruchin.core.utils.updateEntity

data class TrainingExercise(
    override val id: String,
    val name: String,
    val numOfSets: Int,
    val repsRange: IntRange,
    val sets: List<TrainingSet>,
) : Entity {

    val completed: Boolean
        get() = sets.all { it.completed }

    fun completeSet(setId: String, weight: Float, reps: Int) = copy(
        sets = sets.updateEntity(setId) { set ->
            set.copy(
                weight = weight,
                reps = reps
            )
        }
    )
}
