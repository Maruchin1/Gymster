package com.maruchin.model.training

import app.cash.turbine.test
import com.maruchin.model.plan.PlanRepository
import com.maruchin.model.user.UserRepository
import com.maruchin.model.user.sampleUser
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class TrainingUseCaseTest {

    private val trainingRepository = mockk<TrainingRepository>()
    private val userRepository = mockk<UserRepository>()
    private val planRepository = mockk<PlanRepository>()
    private val trainingFactory = mockk<TrainingFactory>()
    private val dispatcher = UnconfinedTestDispatcher()
    private val trainingUseCase = TrainingUseCase(
        trainingRepository,
        userRepository,
        planRepository,
        trainingFactory,
        dispatcher,
    )

    @Test
    fun `Get trainings history`() = runTest {
        // Given
        every { userRepository.getLogged() } returns flowOf(sampleUser)
        every { trainingRepository.getByPlan(sampleUser.preferences.activePlanId) } returns
            flowOf(sampleTrainings.shuffled())

        // When
        trainingUseCase.getTrainingsHistory().test {

            // Then
            assertContentEquals(sampleTrainings, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `Get active training`() = runTest {
        // Given
        every { trainingRepository.getActive() } returns flowOf(sampleTrainings[2])

        // When
        trainingUseCase.getActiveTraining().test {

            // Then
            assertEquals(sampleTrainings[2], awaitItem())
            awaitComplete()
        }
    }
}
