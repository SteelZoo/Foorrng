package com.tasteguys.foorrng_customer.domain.repository

import com.tasteguys.foorrng_customer.domain.model.truck.TruckData
import java.io.File

interface TruckRepository {

    suspend fun reportFoodTruck(
        name: String,
        picture: File,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<Long>

    suspend fun updateFoodTruck(
        foodtruckId: Long,
        name: String,
        picture: File,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<Long>

    suspend fun getTruckList(
        latLeft: Double,
        lngLeft: Double,
        latRight: Double,
        lngRight: Double
    ): Result<List<TruckData>>

}