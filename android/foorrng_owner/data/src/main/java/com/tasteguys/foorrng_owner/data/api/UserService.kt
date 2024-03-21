package com.tasteguys.foorrng_owner.data.api

import com.tasteguys.foorrng_owner.data.model.DefaultResponse
import com.tasteguys.foorrng_owner.data.model.user.LoginResponse
import com.tasteguys.foorrng_owner.data.model.user.UserRequest
import retrofit2.http.Body
import retrofit2.http.POST


interface UserService {
    @POST("user/regist/owner")
    suspend fun registerOwner(
        @Body userRequest: UserRequest
    ) : Result<DefaultResponse<Long>>

    @POST("user/login")
    suspend fun login(
        @Body userRequest: UserRequest
    ) : Result<DefaultResponse<LoginResponse>>
}