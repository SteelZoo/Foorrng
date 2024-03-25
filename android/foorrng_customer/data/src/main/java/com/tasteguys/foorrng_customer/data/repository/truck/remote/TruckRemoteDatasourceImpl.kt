package com.tasteguys.foorrng_customer.data.repository.truck.remote

import com.tasteguys.foorrng_customer.data.api.FoodTruckReportService
import com.tasteguys.foorrng_customer.data.mapper.toNonDefault
import com.tasteguys.foorrng_customer.data.model.truck.TruckRequest
import javax.inject.Inject

class TruckRemoteDatasourceImpl @Inject constructor(
    private val truckService: FoodTruckReportService
) : TruckRemoteDatasource {
    override suspend fun reportFoodTruck(
        name: String,
        picture: String,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<Long> {
        return truckService.reportFoodTruck(
            TruckRequest(
                name, picture, carNumber, announcement, phoneNumber, category
            )
        ).toNonDefault()

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
        return truckService.updateFoodTruck(
            TruckRequest(
                foodtruckId, name, picture, carNumber, announcement, phoneNumber, category
            )
        ).toNonDefault()
    }
}