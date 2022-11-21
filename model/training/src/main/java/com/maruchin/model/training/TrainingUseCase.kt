package com.maruchin.model.training

import com.maruchin.core.utils.DefaultDispatcher
import com.maruchin.core.utils.Id
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
            .map { list ->
                list.sortedWith(compareBy({ it.weekNumber }, { it.planTrainingId.value }))
            }

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
                trainingRepository.add(training)
            }
        }
    }

    suspend fun activateTraining(trainingId: Id) = withContext(dispatcher) {
        trainingRepository.setActive(trainingId)
        trainingRepository.getActive().first()
            ?.begin()
            ?.let { trainingRepository.update(it) }
    }

    suspend fun activateSet(setId: Id) = withContext(dispatcher) {
        trainingRepository.getActive().first()
            ?.activateSet(setId)
            ?.let { trainingRepository.update(it) }
    }

    suspend fun completeActiveSet(weight: Float, reps: Int) = withContext(dispatcher) {
        trainingRepository.getActive().first()
            ?.completeActiveSet(weight, reps)
            ?.let { trainingRepository.update(it) }
    }

    suspend fun activateExercise(exerciseId: Id) = withContext(dispatcher) {
        trainingRepository.getActive().first()
            ?.activateExercise(exerciseId)
            ?.let { trainingRepository.update(it) }
    }

    suspend fun goNext() = withContext(dispatcher) {
        trainingRepository.getActive().first()
            ?.goNext()
            ?.let { trainingRepository.update(it) }
    }

    suspend fun goPrevious() = withContext(dispatcher) {
        trainingRepository.getActive().first()
            ?.goPrevious()
            ?.let { trainingRepository.update(it) }
    }
}
