package com.tasteguys.foorrng_owner.data.model.user

import com.squareup.moshi.Json

data class LoginResponse(
    @Json(name = "TokenDto")
    val tokenDto: TokenDto,
)

data class TokenDto(
    val accessToken: String,
    val refreshToken: String
)
