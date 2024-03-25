package com.tasteguys.foorrng_customer.data.api

import com.tasteguys.foorrng_customer.data.model.DefaultResponse
import com.tasteguys.foorrng_customer.data.model.LocationRequest
import com.tasteguys.foorrng_customer.data.model.truck.TruckListResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckRequest
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part

interface FoodTruckService {
    @Multipart
    @POST("foodtruck-report/regist")
    suspend fun reportFoodTruck(
        @Part picture: MultipartBody.Part,
        @Part("foodtruckDto") truckRequest: TruckRequest
    ): Result<DefaultResponse<Long>>

    @Multipart
    @PATCH("foodtruck-report/update")
    suspend fun updateFoodTruck(
        @Part picture: MultipartBody.Part,
        @Part("foodtruckDto") truckRequest: TruckRequest
    ): Result<DefaultResponse<Long>>

    @GET("foodtrucks")
    suspend fun getTrucks(
        @Body location: LocationRequest
    ): Result<DefaultResponse<List<TruckListResponse>>>

}