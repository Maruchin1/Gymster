package com.maruchin.feature.plandetails.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.data.trainingplan.TrainingPlanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class PlanDetailsViewModel @Inject constructor(
    private val trainingPlanRepository: TrainingPlanRepository,
) : ViewModel() {

    private val planId = MutableStateFlow("")

    val uiState: StateFlow<PlanDetailsUiState> =
        planId
            .flatMapLatest { trainingPlanRepository.getPlanStream(it) }
            .filterNotNull()
            .map { PlanDetailsUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = PlanDetailsUiState(),
            )

    fun selectPlan(id: String) = viewModelScope.launch {
        planId.emit(id)
    }

    fun modifyPlan(newName: String) = viewModelScope.launch {
        trainingPlanRepository
            .getPlanStream(planId.value)
            .first()
            ?.modify(newName)
            ?.let { trainingPlanRepository.save(it) }
    }

    fun modifyTraining(trainingId: String, newName: String) = viewModelScope.launch {
        trainingPlanRepository
            .getPlanStream(planId.value)
            .first()
            ?.modifyTraining(trainingId, newName)
            ?.let { trainingPlanRepository.save(it) }
    }

    fun modifyExercise(
        trainingId: String,
        exerciseId: String,
        name: String,
        numOfSeries: String,
        minReps: String,
        maxReps: String
    ) = viewModelScope.launch {
        trainingPlanRepository
            .getPlanStream(planId.value)
            .first()
            ?.modifyExercise(
                trainingId = trainingId,
                exerciseId = exerciseId,
                newName = name,
                newNumOfSeries = numOfSeries.toInt(),
                newRepsRange = minReps.toInt()..maxReps.toInt()
            )
    }
}
