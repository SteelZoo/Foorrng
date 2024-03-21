package com.tasteguys.foorrng_customer.data.mapper

import com.tasteguys.foorrng_customer.data.model.user.LoginResponse
import com.tasteguys.foorrng_customer.domain.model.user.TokenData

fun LoginResponse.toDomain() = TokenData(
    accessToken = accessToken,
    refreshToken = refreshToken
)