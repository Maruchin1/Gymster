package com.maruchin.model.user

import com.maruchin.core.utils.DefaultDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val userRepository: UserRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend fun selectPlan(planId: String) = withContext(dispatcher) {
        withLoggedUser {
            selectPlan(planId)
        }
    }

    private suspend fun withLoggedUser(reduce: User.() -> User) {
        userRepository
            .getLogged()
            .first()
            .let(reduce)
            .let { userRepository.saveLogged(it) }
    }
}
