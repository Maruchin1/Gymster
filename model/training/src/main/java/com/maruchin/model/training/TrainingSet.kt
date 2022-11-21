package com.maruchin.model.training

import com.maruchin.core.utils.Entity
import com.maruchin.core.utils.Id

data class TrainingSet(
    override val id: Id = Id.generate(),
    val number: String,
    var weight: Float = 0f,
    var reps: Int = 0,
) : Entity {

    val completed: Boolean
        get() = weight > 0f && reps > 0

    fun complete(weight: Float, reps: Int) = copy(
        weight = weight,
        reps = reps
    )
}
