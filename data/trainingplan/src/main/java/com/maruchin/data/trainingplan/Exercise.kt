package com.maruchin.data.trainingplan

import com.maruchin.core.utils.Entity
import com.maruchin.core.utils.generateId

data class Exercise(
    override val id: String = generateId(),
    val name: String,
    val numOfSeries: Int,
    val repsRange: IntRange,
) : Entity {

    companion object {

        fun placeholder() = Exercise(
            name = "Wyciskanie",
            numOfSeries = 3,
            repsRange = 4..6,
        )
    }
}
