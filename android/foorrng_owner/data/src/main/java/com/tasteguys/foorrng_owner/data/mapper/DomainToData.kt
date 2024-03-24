package com.tasteguys.foorrng_owner.data.mapper

import com.tasteguys.foorrng_owner.data.model.user.LoginResponse
import com.tasteguys.foorrng_owner.domain.model.user.LoginData

fun LoginResponse.toDomain() = LoginData(
    isBusiRegist = isBusiRegist,
    accessToken = accessToken,
    refreshToken = refreshToken
)