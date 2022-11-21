package com.maruchin.model.user

import com.maruchin.core.utils.DefaultDispatcher
import com.maruchin.core.utils.Id
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun selectPlan(planId: Id) = withContext(dispatcher) {
        userRepository.getLogged().first()
            .selectPlan(planId)
            .let { userRepository.saveLogged(it) }
    }
}
