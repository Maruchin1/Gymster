package com.maruchin.feature.planslist.screen

import com.maruchin.data.trainingplan.TrainingPlan

internal data class PlansListUiState(
    val plans: List<PlanUiState> = emptyList(),
) {
    constructor(entities: Collection<TrainingPlan>) : this(
        plans = entities.map { PlanUiState(it) }
    )
}

internal data class PlanUiState(
    val planId: String = "",
    val name: String = "",
) {
    constructor(plan: TrainingPlan) : this(
        planId = plan.id,
        name = plan.name,
    )
}
