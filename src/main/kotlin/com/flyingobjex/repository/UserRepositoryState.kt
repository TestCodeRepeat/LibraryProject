package com.flyingobjex.repository

import com.flyingobjex.model.user.LibraryUser

data class UserRepositoryState(
    val users: List<LibraryUser>
)