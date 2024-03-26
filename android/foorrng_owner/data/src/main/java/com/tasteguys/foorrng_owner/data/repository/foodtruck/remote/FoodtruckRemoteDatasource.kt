package com.tasteguys.foorrng_owner.data.repository.foodtruck.remote

import java.io.File

interface FoodtruckRemoteDatasource {
    suspend fun registFoodtruck(
        name: String,
        carNumber: String,
        accountInfo: String,
        phoneNumber: String,
        announcement: String,
        category: List<String>,
        picture: File?
    ) : Result<Boolean>
}