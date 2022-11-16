package com.maruchin.ui.training

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.model.plan.PlanUseCase
import com.maruchin.model.training.TrainingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class TrainingViewModel @Inject constructor(
    private val trainingUseCase: TrainingUseCase,
    private val planUseCase: PlanUseCase,
) : ViewModel() {

    val uiState: StateFlow<TrainingUiState> =
        combine(
            planUseCase.getActivePlan(),
            trainingUseCase.getActiveTraining().filterNotNull(),
        ) { plan, training ->
            TrainingUiState(plan, training)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = TrainingUiState()
        )

    fun goToNextExercise() = viewModelScope.launch {
        trainingUseCase.goToNextExercise()
    }

    fun goToPreviousExercise() = viewModelScope.launch {
        trainingUseCase.goToPreviousExercise()
    }

    fun goToExercise(exerciseId: String) = viewModelScope.launch {
        trainingUseCase.setActiveExercise(exerciseId)
    }

    fun editSet(setId: String) = viewModelScope.launch {
        trainingUseCase.setActiveSet(setId)
    }
}
