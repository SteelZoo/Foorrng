package com.tasteguys.foorrng_customer.data.repository.truck.remote

import com.tasteguys.foorrng_customer.data.model.user.LoginResponse

interface TruckRemoteDatasource {
    suspend fun reportFoodTruck(
        name: String,
        picture: String,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<Long>

    suspend fun updateFoodTruck(
        foodtruckId: Long,
        name: String,
        picture: String,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<Long>
}