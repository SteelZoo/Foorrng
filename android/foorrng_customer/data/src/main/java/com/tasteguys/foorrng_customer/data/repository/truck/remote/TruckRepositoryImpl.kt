package com.tasteguys.foorrng_customer.data.repository.truck.remote

import com.tasteguys.foorrng_customer.domain.repository.TruckRepository
import javax.inject.Inject

class TruckRepositoryImpl @Inject constructor(
    private val truckRemoteDatasource: TruckRemoteDatasource
): TruckRepository {
    override suspend fun reportFoodTruck(
        name: String,
        picture: String,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<Long> {
        return truckRemoteDatasource.reportFoodTruck(
            name, picture, carNumber, announcement, phoneNumber, category
        )
    }

    override suspend fun updateFoodTruck(
        foodtruckId: Long,
        name: String,
        picture: String,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<Long> {
        return truckRemoteDatasource.updateFoodTruck(
            foodtruckId, name, picture, carNumber, announcement, phoneNumber, category
        )
    }
}