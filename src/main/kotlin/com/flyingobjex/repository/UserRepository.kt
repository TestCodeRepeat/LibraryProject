package com.flyingobjex.repository

import com.flyingobjex.data.generators.DataGenerator
import com.flyingobjex.model.user.LibraryUser
import com.flyingobjex.model.user.LibraryUserStatus
import java.util.UUID
import kotlinx.coroutines.flow.MutableStateFlow


class UserRepository {
    private val userState: MutableStateFlow<UserRepositoryState>

    init {
        userState = MutableStateFlow(initState())
    }

    private fun initState(): UserRepositoryState {
        return UserRepositoryState(users = emptyList())
    }

    fun generateAnonUser(): LibraryUser {
        return LibraryUser(UUID.randomUUID().toString(), DataGenerator.generateUserName(), LibraryUserStatus.ACTIVE)
    }

    fun registerUser(user: LibraryUser): LibraryUser {
        userState.value = userState.value.copy(users = userState.value.users + user)
        return user
    }
}