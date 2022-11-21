package com.maruchin.ui.set

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.model.training.TrainingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class SetViewModel @Inject constructor(
    private val trainingUseCase: TrainingUseCase
) : ViewModel() {

    val uiState: StateFlow<SetUiState> =
        combine(
            trainingUseCase.getActiveTraining().filterNotNull(),
            trainingUseCase.getPreviousTraining(),
        ) { training, previousSet ->
            SetUiState(training, previousSet)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SetUiState()
        )

    fun completeSet(weightAngle: Int, repsAngle: Int) = viewModelScope.launch {
        trainingUseCase.completeActiveSet(
            weight = angleToWeight(weightAngle),
            reps = angleToReps(repsAngle)
        )
    }
}
