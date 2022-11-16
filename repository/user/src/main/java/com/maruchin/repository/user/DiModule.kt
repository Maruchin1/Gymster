package com.maruchin.repository.user

import com.maruchin.model.user.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface DiModule {

    @Binds
    fun userRepository(impl: MemoryUserRepository): UserRepository
}
