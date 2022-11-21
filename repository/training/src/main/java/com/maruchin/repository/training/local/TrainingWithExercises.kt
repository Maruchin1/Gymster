package com.maruchin.repository.training.local

import androidx.room.Embedded
import androidx.room.Relation
import com.maruchin.core.utils.Id
import com.maruchin.model.training.Training

data class TrainingWithExercises(
    @Embedded
    val training: TrainingEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "training_id",
        entity = TrainingExerciseEntity::class
    )
    val exercises: List<TrainingExerciseWithSets>,
)

internal fun TrainingWithExercises.asModel() = Training(
    id = Id(training.id),
    planId = Id(training.planId ?: ""),
    planTrainingId = Id(training.planTrainingId ?: ""),
    weekNumber = training.weekNumber ?: 0,
    exercises = exercises.map { it.asModel() },
    activeExerciseId = Id(training.activeExerciseId ?: ""),
    activeSetId = Id(training.activeSetId ?: "")
)

internal fun Training.asDb() = TrainingWithExercises(
    training = TrainingEntity(
        id = id.value,
        planId = planId.value,
        planTrainingId = planTrainingId.value,
        weekNumber = weekNumber,
        activeExerciseId = activeExerciseId.value,
        activeSetId = activeSetId.value,
    ),
    exercises = exercises.map { it.asDb(id.value) }
)
