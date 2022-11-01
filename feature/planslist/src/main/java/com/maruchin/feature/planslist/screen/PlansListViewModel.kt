package com.maruchin.feature.planslist.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maruchin.data.trainingplan.TrainingPlanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class PlansListViewModel @Inject constructor(
    private val trainingPlanRepository: TrainingPlanRepository,
) : ViewModel() {

    val state: StateFlow<PlansListUiState> =
        trainingPlanRepository
            .getPlansStream()
            .map { PlansListUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = PlansListUiState()
            )
}
