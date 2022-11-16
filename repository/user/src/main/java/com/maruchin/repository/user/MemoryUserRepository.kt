package com.maruchin.repository.user

import com.maruchin.model.user.User
import com.maruchin.model.user.UserRepository
import com.maruchin.model.user.sampleUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class MemoryUserRepository @Inject constructor() : UserRepository {

    private val data = MutableStateFlow(sampleUser)

    override fun getLogged(): Flow<User> = data

    override suspend fun saveLogged(user: User) {
        data.emit(user)
    }
}
