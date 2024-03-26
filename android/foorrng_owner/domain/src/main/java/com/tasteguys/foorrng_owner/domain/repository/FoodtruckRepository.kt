package com.tasteguys.foorrng_owner.domain.repository

import java.io.File

interface FoodtruckRepository {
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