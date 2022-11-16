package com.maruchin.model.plan

import com.maruchin.model.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

class PlanUseCase @Inject constructor(
    private val planRepository: PlanRepository,
    private val userRepository: UserRepository,
) {

    fun getActivePlan(): Flow<Plan> =
        userRepository
            .getLogged()
            .flatMapLatest { planRepository.getById(it.preferences.activePlanId) }
            .filterNotNull()
}
