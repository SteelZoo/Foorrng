package com.tasteguys.foorrng_customer.domain.usecase.truck

import com.tasteguys.foorrng_customer.domain.repository.TruckRepository
import java.io.File
import javax.inject.Inject

class RegisterTruckUseCase @Inject constructor(
    private val truckRepository: TruckRepository
) {
    suspend operator fun invoke(
        name: String,
        picture: File,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<Long>{
        return truckRepository.reportFoodTruck(
            name, picture, carNumber, announcement, phoneNumber, category
        )
    }
}