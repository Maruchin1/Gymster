package com.maruchin.model.plan

import app.cash.turbine.test
import com.maruchin.model.user.UserRepository
import com.maruchin.model.user.sampleUser
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class PlanUseCaseTest {

    private val planRepository = mockk<PlanRepository>()
    private val userRepository = mockk<UserRepository>()
    private val planUseCase = PlanUseCase(planRepository, userRepository)

    @Test
    fun `Get active plan`() = runTest {
        // Given
        every { userRepository.getLogged() } returns flowOf(sampleUser)
        every { planRepository.getById(sampleUser.preferences.activePlanId) } returns
            flowOf(samplePushPullPlan)

        // When
        planUseCase.getActivePlan().test {

            // Then
            assertEquals(samplePushPullPlan, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `Can't get active plan`() = runTest {
        // Given
        every { userRepository.getLogged() } returns flowOf(sampleUser)
        every { planRepository.getById(sampleUser.preferences.activePlanId) } returns flowOf(null)

        // When
        planUseCase.getActivePlan().test {

            // Then
            expectNoEvents()
        }
    }
}
