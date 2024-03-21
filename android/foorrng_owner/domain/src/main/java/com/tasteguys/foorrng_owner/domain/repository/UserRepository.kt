package com.tasteguys.foorrng_owner.domain.repository

import com.tasteguys.foorrng_owner.domain.model.user.TokenData

interface UserRepository {
    suspend fun registerOwner(
        userUid: Long,
        name: String,
        email: String
    ): Result<Long>

    suspend fun login(
        userUid: Long,
        name: String,
        email: String
    ): Result<TokenData>
}