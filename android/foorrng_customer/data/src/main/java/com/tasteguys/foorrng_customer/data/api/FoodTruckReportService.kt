package com.tasteguys.foorrng_customer.data.api

import com.tasteguys.foorrng_customer.data.model.DefaultResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckRequest
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST

interface FoodTruckReportService {
    @POST("/foodtruck-report/regist")
    suspend fun reportFoodTruck(
        @Body truckRequest: TruckRequest
    ): Result<DefaultResponse<Long>>

    @PATCH("/foodtruck-report/update")
    suspend fun updateFoodTruck(
        @Body truckRequest: TruckRequest
    ): Result<DefaultResponse<Long>>
}