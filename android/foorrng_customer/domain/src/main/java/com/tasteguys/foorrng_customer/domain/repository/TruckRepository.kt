package com.tasteguys.foorrng_customer.domain.repository

interface TruckRepository {

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