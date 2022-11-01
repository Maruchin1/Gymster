package com.maruchin.data.trainingplan

import com.maruchin.core.utils.Entity
import com.maruchin.core.utils.generateId

data class Training(
    override val id: String = generateId(),
    val name: String,
    val exercises: List<Exercise>,
) : Entity {

    companion object {

        fun placeholder(ordinalNum: Int) = Training(
            name = "Trening $ordinalNum",
            exercises = listOf(Exercise.placeholder())
        )
    }
}
