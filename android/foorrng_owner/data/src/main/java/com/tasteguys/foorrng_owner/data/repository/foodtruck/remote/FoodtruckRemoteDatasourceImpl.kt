package com.tasteguys.foorrng_owner.data.repository.foodtruck.remote

import com.tasteguys.foorrng_owner.data.api.FoodtruckService
import com.tasteguys.foorrng_owner.data.mapper.toNonDefault
import com.tasteguys.foorrng_owner.data.model.foodtruck.FoodtruckDetailResponse
import com.tasteguys.foorrng_owner.data.model.foodtruck.FoodtruckRegistRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class FoodtruckRemoteDatasourceImpl @Inject constructor(
    private val foodtruckService: FoodtruckService
) : FoodtruckRemoteDatasource {
    override suspend fun registFoodtruck(
        name: String,
        carNumber: String,
        accountInfo: String,
        phoneNumber: String,
        announcement: String,
        category: List<String>,
        picture: File?
    ): Result<Boolean> {
        val image = picture?.asRequestBody("image/jpg".toMediaTypeOrNull())
        val multipartImage = image?.let {
            MultipartBody.Part.createFormData("picture", picture.name, it)
        }

        val request = FoodtruckRegistRequest(
            name = name,
            carNumber = carNumber,
            accountInfo = accountInfo,
            phoneNumber = phoneNumber,
            announcement = announcement,
            category = category
        )

        return foodtruckService.registFoodtruck(multipartImage, request)
            .toNonDefault()
            .map {
                it.foodtruckId >= 0L
            }
    }

    override suspend fun getFoodtruck(): Result<FoodtruckDetailResponse> {
        return foodtruckService.getFoodtruck().toNonDefault()
    }
}