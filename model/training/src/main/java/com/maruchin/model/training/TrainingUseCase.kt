package com.maruchin.model.training

import com.maruchin.core.utils.DefaultDispatcher
import com.maruchin.model.plan.PlanRepository
import com.maruchin.model.user.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrainingUseCase @Inject internal constructor(
    private val trainingRepository: TrainingRepository,
    private val userRepository: UserRepository,
    private val planRepository: PlanRepository,
    private val trainingFactory: TrainingFactory,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) {

    fun getTrainingsHistory(): Flow<List<Training>> =
        userRepository
            .getLogged()
            .map { it.preferences.activePlanId }
            .flatMapLatest { trainingRepository.getByPlan(it) }
            .map { list -> list.sortedWith(compareBy({ it.weekNumber }, { it.planDayId })) }

    fun getActiveTraining(): Flow<Training?> =
        trainingRepository.getActive()

    fun getPreviousTraining(): Flow<Training?> =
        trainingRepository.getPrevious()

    suspend fun startNewWeek() {
        val user = userRepository.getLogged().first()
        val planId = user.preferences.activePlanId
        planRepository.getById(planId).first()?.let { plan ->
            val trainingHistory = trainingRepository.getByPlan(plan.id).first()
            val newTrainingWeek = trainingFactory.createNewWeek(plan, trainingHistory)
            newTrainingWeek.forEach { training ->
                trainingRepository.save(training)
            }
        }
    }

    suspend fun setActiveTraining(trainingId: String) = withContext(dispatcher) {
        trainingRepository.setActive(trainingId)
    }

    suspend fun setActiveExercise(exerciseId: String) = withContext(dispatcher) {
        withActiveTraining { activateExercise(exerciseId) }
    }

    suspend fun setActiveSet(setId: String) = withContext(dispatcher) {
        withActiveTraining { activateSet(setId) }
    }

    suspend fun goToNextExercise() = withContext(dispatcher) {
        withActiveTraining { goToNextExercise() }
    }

    suspend fun goToPreviousExercise() = withContext(dispatcher) {
        withActiveTraining { goToPreviousExercise() }
    }

    suspend fun completeActiveSet(weight: Float, reps: Int) = withContext(dispatcher) {
        withActiveTraining { completeActiveSet(weight, reps) }
    }

    private suspend fun withActiveTraining(action: Training.() -> Training) {
        trainingRepository
            .getActive()
            .first()
            ?.let(action)
            ?.let { trainingRepository.save(it) }
    }
}
