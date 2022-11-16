package com.maruchin.model.training

import com.maruchin.core.utils.Entity

data class TrainingSet(
    override val id: String,
    val weight: Float = 0f,
    val reps: Int = 0,
) : Entity {

    val completed: Boolean
        get() = weight > 0f && reps > 0
}
