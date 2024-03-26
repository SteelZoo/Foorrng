package com.tasteguys.foorrng_owner.data.api

import com.tasteguys.foorrng_owner.data.model.DefaultResponse
import com.tasteguys.foorrng_owner.data.model.foodtruck.FoodtruckRegistRequest
import com.tasteguys.foorrng_owner.data.model.foodtruck.FoodtruckRegistResponse
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface FoodtruckService {
    @POST("foodtruck/regist")
    suspend fun registFoodtruck(
        @Part picture: MultipartBody.Part?,
        @Part("foodtruckCreateDto") foodtruckCreateDto: FoodtruckRegistRequest
    ) : Result<DefaultResponse<FoodtruckRegistResponse>>
}