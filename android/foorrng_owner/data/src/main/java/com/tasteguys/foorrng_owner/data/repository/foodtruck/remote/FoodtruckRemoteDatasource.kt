package com.tasteguys.foorrng_owner.data.repository.foodtruck.remote

import com.tasteguys.foorrng_owner.data.model.DefaultResponse
import com.tasteguys.foorrng_owner.data.model.foodtruck.FoodtruckDetailResponse
import com.tasteguys.foorrng_owner.data.model.mark.MarkResponse
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

    suspend fun getFoodtruck() : Result<FoodtruckDetailResponse>

    suspend fun updateFoodtruck(
        foodtruckId: Long,
        name: String,
        carNumber: String,
        accountInfo: String,
        phoneNumber: String,
        announcement: String,
        category: List<String>,
        picture: File?
    ) : Result<Boolean>

    suspend fun getMarkList(): Result<List<MarkResponse>>
}