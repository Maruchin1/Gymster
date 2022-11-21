package com.maruchin.model.training

import com.maruchin.core.utils.Entity
import com.maruchin.core.utils.Id
import com.maruchin.core.utils.updateEntity

data class TrainingExercise(
    override val id: Id = Id.generate(),
    val number: String,
    val name: String,
    val numOfSets: Int,
    val repsRange: IntRange,
    val sets: List<TrainingSet>,
) : Entity {

    val completed: Boolean
        get() = sets.all { it.completed }

    init {
        if (sets.size != numOfSets) {
            throw IllegalStateException("Sets list does not match num of setsŌ")
        }
    }

    fun completeSet(setId: Id, weight: Float, reps: Int) = copy(
        sets = sets.updateEntity(setId) { set ->
            set.complete(weight, reps)
        }
    )
}
