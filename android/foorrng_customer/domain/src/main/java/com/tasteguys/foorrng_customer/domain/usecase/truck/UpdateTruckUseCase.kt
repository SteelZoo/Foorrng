package com.tasteguys.foorrng_customer.domain.usecase.truck

import com.tasteguys.foorrng_customer.domain.repository.TruckRepository
import javax.inject.Inject

class UpdateTruckUseCase @Inject constructor(
    private val truckRepository: TruckRepository
) {
    suspend operator fun invoke(
        foodtruckId: Long,
        name: String,
        picture: String,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<Long>{
        return truckRepository.updateFoodTruck(
            foodtruckId, name, picture, carNumber, announcement, phoneNumber, category
        )
    }
}