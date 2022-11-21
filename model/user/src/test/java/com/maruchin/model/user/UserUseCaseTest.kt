package com.maruchin.model.user

import com.maruchin.core.utils.Id
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
        val updatedUser = sampleUser.selectPlan(Id("1"))
        every { userRepository.getLogged() } returns flowOf(sampleUser)
        coJustRun { userRepository.saveLogged(updatedUser) }

        // When
        userUseCase.selectPlan(Id("1"))

        // Then
        coVerify { userRepository.saveLogged(updatedUser) }
    }
}
