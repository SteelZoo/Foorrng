package com.tasteguys.foorrng_owner.data.api

import retrofit2.http.POST


interface UserServie {
//    @POST("user/token/refresh")
//    suspend fun refreshToken()

    @POST("user/register/owner")
    suspend fun registerOwner()

    @POST("user/login")
    suspend fun login()
}