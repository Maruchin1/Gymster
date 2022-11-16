package com.maruchin.model.user

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getLogged(): Flow<User>

    suspend fun saveLogged(user: User)
}
