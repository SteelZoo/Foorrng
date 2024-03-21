package com.tasteguys.foorrng_owner.data.model.user

import com.squareup.moshi.Json

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String
)
