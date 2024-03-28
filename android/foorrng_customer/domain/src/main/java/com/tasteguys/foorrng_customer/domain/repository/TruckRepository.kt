package com.tasteguys.foorrng_customer.domain.repository

import com.tasteguys.foorrng_customer.domain.model.truck.FavoriteTruckData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckDetailData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckDetailMarkData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckOperationData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckRegisterUpdateData
import java.io.File

interface TruckRepository {

    suspend fun reportFoodTruck(
        name: String,
        picture: File?,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<TruckRegisterUpdateData>

    suspend fun updateFoodTruck(
        foodtruckId: Long,
        name: String,
        picture: File?,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<TruckRegisterUpdateData>

    suspend fun getTruckList(
        latLeft: Double,
        lngLeft: Double,
        latRight: Double,
        lngRight: Double
    ): Result<List<TruckData>>

    suspend fun getTruckDetail(
        truckId: Long
    ): Result<TruckDetailData>

    suspend fun markFavoriteTruck(
        truckId: Long
    ): Result<Long>

    suspend fun getFavoriteTruckList(): Result<List<FavoriteTruckData>>

    suspend fun registerTruckInfo(
        truckId: Long,
        address: String,
        lat: Double,
        lng: Double,
        operationInfo: List<TruckOperationData>
    ): Result<TruckDetailMarkData>

}