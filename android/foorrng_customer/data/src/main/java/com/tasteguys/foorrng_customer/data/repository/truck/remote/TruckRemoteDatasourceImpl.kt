package com.tasteguys.foorrng_customer.data.repository.truck.remote

import com.tasteguys.foorrng_customer.data.api.FoodTruckService
import com.tasteguys.foorrng_customer.data.mapper.toNonDefault
import com.tasteguys.foorrng_customer.data.model.LocationRequest
import com.tasteguys.foorrng_customer.data.model.truck.TruckDetailResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckFavoriteListResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckListResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckRequest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

class TruckRemoteDatasourceImpl @Inject constructor(
    private val truckService: FoodTruckService
) : TruckRemoteDatasource {
    override suspend fun reportFoodTruck(
        name: String,
        picture: File,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<Long> {
        val requestFile = picture.asRequestBody("image/*".toMediaTypeOrNull())
        return truckService.reportFoodTruck(
            TruckRequest(
                name, carNumber, announcement, phoneNumber, category
            ),
            MultipartBody.Part.createFormData("picture", picture.name, requestFile),
        ).toNonDefault()
    }

    override suspend fun updateFoodTruck(
        foodtruckId: Long,
        name: String,
        picture: File,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<Long> {
        val requestFile = picture.asRequestBody("image/*".toMediaTypeOrNull())
        return truckService.updateFoodTruck(
            TruckRequest(
                foodtruckId, name, carNumber, announcement, phoneNumber, category
            ),
            MultipartBody.Part.createFormData("picture", picture.name, requestFile),

        ).toNonDefault()
    }

    override suspend fun getTruckList(
        latLeft: Double,
        lngLeft: Double,
        latRight: Double,
        lngRight: Double
    ): Result<List<TruckListResponse>> {
        return truckService.getTrucks(
            LocationRequest(latLeft, lngLeft, latRight, lngRight)
        ).toNonDefault()
    }

    override suspend fun getTruckDetail(truckId: Long): Result<TruckDetailResponse> {
        return truckService.getTruckDetail(truckId).toNonDefault()
    }

    override suspend fun markFavoriteTruck(truckId: Long): Result<Long> {
        return truckService.markFavoriteFoodTruck(truckId).toNonDefault()
    }

    override suspend fun getFavoriteTruckList(): Result<List<TruckFavoriteListResponse>> {
        return truckService.getFavoriteTrucks().toNonDefault()
    }
}