package com.maruchin.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.model.plan.PlanUseCase
import com.maruchin.model.training.TrainingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val planUseCase: PlanUseCase,
    private val trainingUseCase: TrainingUseCase,
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> =
        combine(
            planUseCase.getActivePlan(),
            trainingUseCase.getTrainingsHistory(),
        ) { plan, trainings ->
            HomeUiState(plan, trainings)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = HomeUiState()
        )

    fun startNewWeek() = viewModelScope.launch {
        trainingUseCase.startNewWeek()
    }

    fun startTraining(trainingId: String) = viewModelScope.launch {
        trainingUseCase.setActiveTraining(trainingId)
    }
}
