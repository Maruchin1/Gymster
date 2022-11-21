package com.maruchin.repository.training

import com.maruchin.model.training.TrainingRepository
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.Module
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DiModule {

    @Binds
    fun repository(impl: TrainingRepositoryImpl): TrainingRepository
}
