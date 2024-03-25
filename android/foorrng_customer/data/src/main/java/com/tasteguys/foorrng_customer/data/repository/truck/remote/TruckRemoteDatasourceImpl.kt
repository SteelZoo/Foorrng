package com.tasteguys.foorrng_customer.data.repository.truck.remote

import com.tasteguys.foorrng_customer.data.api.FoodTruckService
import com.tasteguys.foorrng_customer.data.mapper.toNonDefault
import com.tasteguys.foorrng_customer.data.model.LocationRequest
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
            MultipartBody.Part.createFormData("file", picture.name, requestFile),
            TruckRequest(
                name, carNumber, announcement, phoneNumber, category
            )
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
            MultipartBody.Part.createFormData("file", picture.name, requestFile),
            TruckRequest(
                foodtruckId, name, carNumber, announcement, phoneNumber, category
            )
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
}