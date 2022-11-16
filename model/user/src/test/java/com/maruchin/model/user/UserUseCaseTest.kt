package com.maruchin.model.user

import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserUseCaseTest {

    private val userRepository = mockk<UserRepository>()
    private val dispatcher = UnconfinedTestDispatcher()
    private val userUseCase = UserUseCase(userRepository, dispatcher)

    @Test
    fun `Select plan`() = runTest {
        // Given
        val user = spyk(sampleUser)
        every { userRepository.getLogged() } returns flowOf(user)
        coJustRun { userRepository.saveLogged(any()) }

        // When
        userUseCase.selectPlan("p2")

        // Then
        coVerify { user.selectPlan("p2") }
        coVerify { userRepository.saveLogged(any()) }
    }
}
