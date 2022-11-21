package com.maruchin.model.training

import app.cash.turbine.test
import com.maruchin.core.utils.Id
import com.maruchin.model.plan.PlanRepository
import com.maruchin.model.plan.samplePushPullPlan
import com.maruchin.model.user.UserRepository
import com.maruchin.model.user.sampleUser
import io.mockk.*
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
            flowOf(listOf(samplePushTraining, samplePullTraining))

        // When
        trainingUseCase.getTrainingsHistory().test {

            // Then
            assertContentEquals(listOf(samplePushTraining, samplePullTraining), awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `Get active training`() = runTest {
        // Given
        every { trainingRepository.getActive() } returns flowOf(samplePushTraining)

        // When
        trainingUseCase.getActiveTraining().test {

            // Then
            assertEquals(samplePushTraining, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `Get previous training`() = runTest {
        // Given
        every { trainingRepository.getPrevious() } returns flowOf(samplePushTraining)

        // When
        trainingUseCase.getPreviousTraining().test {

            // Then
            assertEquals(samplePushTraining, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun `Start first week`() = runTest {
        // Given
        every { userRepository.getLogged() } returns flowOf(sampleUser)
        every { planRepository.getById(sampleUser.preferences.activePlanId) } returns
            flowOf(samplePushPullPlan)
        every { trainingRepository.getByPlan(samplePushPullPlan.id) } returns flowOf(emptyList())
        every { trainingFactory.createNewWeek(samplePushPullPlan, emptyList()) } returns
            listOf(samplePushTraining, samplePullTraining)
        coJustRun { trainingRepository.add(any()) }

        // When
        trainingUseCase.startNewWeek()

        // Then
        coVerify {
            trainingRepository.add(samplePushTraining)
            trainingRepository.add(samplePullTraining)
        }
    }

    @Test
    fun `Start next week`() = runTest {
        // Given
        every { userRepository.getLogged() } returns flowOf(sampleUser)
        every { planRepository.getById(sampleUser.preferences.activePlanId) } returns
            flowOf(samplePushPullPlan)
        every { trainingRepository.getByPlan(samplePushPullPlan.id) } returns
            flowOf(sampleTrainingHistory)
        every { trainingFactory.createNewWeek(samplePushPullPlan, sampleTrainingHistory) } returns
            sampleNewWeek
        coJustRun { trainingRepository.add(any()) }

        // When
        trainingUseCase.startNewWeek()

        // Then
        coVerify {
            trainingRepository.add(sampleNextPushTraining)
            trainingRepository.add(sampleNextPullTraining)
        }
    }

    @Test
    fun `Activate training`() = runTest {
        // Given
        val trainingId = Id("1")
        val beganTraining = samplePushTraining.begin()
        coJustRun { trainingRepository.setActive(trainingId) }
        every { trainingRepository.getActive() } returns flowOf(beganTraining)
        coJustRun { trainingRepository.update(any()) }

        // When
        trainingUseCase.activateTraining(trainingId)

        // Then
        coVerify {
            trainingRepository.setActive(trainingId)
            trainingRepository.update(beganTraining)
        }
    }

    @Test
    fun `Activate set`() = runTest {
        // Given
        val setId = Id("1")
        val beganTraining = samplePushTraining.begin()
        val updatedTraining = beganTraining.activateSet(setId)
        every { trainingRepository.getActive() } returns flowOf(beganTraining)
        coJustRun { trainingRepository.update(any()) }

        // When
        trainingUseCase.activateSet(setId)

        // Then
        coVerify { trainingRepository.update(updatedTraining) }
    }

    @Test
    fun `Complete active set`() = runTest {
        // Given
        val setId = Id("1")
        val weight = 80f
        val reps = 6
        val beganTraining = samplePushTraining.begin().activateSet(setId)
        val updatedTraining = beganTraining.completeActiveSet(weight, reps)
        every { trainingRepository.getActive() } returns flowOf(beganTraining)
        coJustRun { trainingRepository.update(any()) }

        // When
        trainingUseCase.completeActiveSet(weight, reps)

        // Then
        coVerify { trainingRepository.update(updatedTraining) }
    }

     @Test
     fun `Activate exercise`() = runTest {
         // Given
         val exerciseId = Id("2")
         val beganTraining = samplePushTraining.begin()
         val updatedTraining = beganTraining.activateExercise(exerciseId)
         every { trainingRepository.getActive() } returns flowOf(beganTraining)
         coJustRun { trainingRepository.update(any()) }

         // When
         trainingUseCase.activateExercise(exerciseId)

         // Then
         coVerify { trainingRepository.update(updatedTraining) }
     }

    @Test
    fun `Go next`() = runTest {
        // Given
        val beganTraining = samplePushTraining.begin()
        val updatedTraining = beganTraining.goNext()
        every { trainingRepository.getActive() } returns flowOf(beganTraining)
        coJustRun { trainingRepository.update(any()) }

        // When
        trainingUseCase.goNext()

        // Then
        coVerify { trainingRepository.update(updatedTraining) }
    }

    @Test
    fun `Go previous`() = runTest {
        // Given
        val beganTraining = samplePushTraining.begin().goNext()
        val updatedTraining = beganTraining.goPrevious()
        every { trainingRepository.getActive() } returns flowOf(beganTraining)
        coJustRun { trainingRepository.update(any()) }

        // When
        trainingUseCase.goPrevious()

        // Then
        coVerify { trainingRepository.update(updatedTraining) }
    }
}
