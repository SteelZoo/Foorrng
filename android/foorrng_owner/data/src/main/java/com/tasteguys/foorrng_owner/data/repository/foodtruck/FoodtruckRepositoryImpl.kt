package com.tasteguys.foorrng_owner.data.repository.foodtruck

import com.tasteguys.foorrng_owner.data.repository.foodtruck.remote.FoodtruckRemoteDatasource
import com.tasteguys.foorrng_owner.domain.repository.FoodtruckRepository
import java.io.File
import javax.inject.Inject

class FoodtruckRepositoryImpl @Inject constructor(
    private val foodtruckRemoteDatasource: FoodtruckRemoteDatasource
) : FoodtruckRepository {
    override suspend fun registFoodtruck(
        name: String,
        carNumber: String,
        accountInfo: String,
        phoneNumber: String,
        announcement: String,
        category: List<String>,
        picture: File?
    ): Result<Boolean> {
        return foodtruckRemoteDatasource.registFoodtruck(
            name = name,
            carNumber = carNumber,
            accountInfo = accountInfo,
            phoneNumber = phoneNumber,
            announcement = announcement,
            category = category,
            picture = picture
        )
    }
}