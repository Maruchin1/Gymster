package com.maruchin.repository.plan

import com.maruchin.model.plan.PlanRepository
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Module

@Module
@InstallIn(SingletonComponent::class)
internal interface DiModule {

    @Binds
    fun repository(impl: CachingPlanRepository): PlanRepository
}
