package com.tasteguys.foorrng_owner.data.mapper

import com.tasteguys.foorrng_owner.data.model.user.LoginResponse
import com.tasteguys.foorrng_owner.domain.model.user.TokenData

fun LoginResponse.toDomain() = TokenData(
    accessToken = accessToken,
    refreshToken = refreshToken
)