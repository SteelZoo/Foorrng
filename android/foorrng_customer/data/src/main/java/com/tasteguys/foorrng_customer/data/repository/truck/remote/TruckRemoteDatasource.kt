package com.tasteguys.foorrng_customer.data.repository.truck.remote

import com.tasteguys.foorrng_customer.data.model.truck.TruckRegisterUpdateResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckDetailResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckFavoriteListResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckListResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckMenuResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckOperationInfo
import com.tasteguys.foorrng_customer.data.model.truck.TruckOperationInfoDto
import com.tasteguys.foorrng_customer.data.model.truck.TruckRegisterOperationResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckRegisterReviewResponse
import com.tasteguys.foorrng_customer.domain.model.truck.TruckOperationData
import java.io.File

interface TruckRemoteDatasource {
    suspend fun reportFoodTruck(
        name: String,
        picture: File?,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<TruckRegisterUpdateResponse>

    suspend fun updateFoodTruck(
        foodtruckId: Long,
        name: String,
        picture: File?,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<TruckRegisterUpdateResponse>

    suspend fun getTruckList(
        latLeft: Double,
        lngLeft: Double,
        latRight: Double,
        lngRight: Double
    ): Result<List<TruckListResponse>>

    suspend fun getTruckDetail(
        truckId: Long
    ): Result<TruckDetailResponse>

    suspend fun markFavoriteTruck(
        truckId: Long
    ): Result<String>

    suspend fun getFavoriteTruckList(): Result<List<TruckFavoriteListResponse>>

    suspend fun reportFoodTruckOperationInfo(
        truckId: Long,
        address: String,
        lat: Double,
        lng: Double,
        operationInfo: List<TruckOperationInfo>
    ):Result<TruckRegisterOperationResponse>

    suspend fun registerReview(
        truckId: Long,
        rvIsDelicious: Boolean,
        rvIsCool: Boolean,
        rvIsClean: Boolean,
        rvIsKind: Boolean,
        rvIsSpecial: Boolean,
        rvIsCheap: Boolean,
        rvIsFast: Boolean
    ): Result<TruckRegisterReviewResponse>

    suspend fun getMenu(
        truckId: Long
    ): Result<List<TruckMenuResponse>>

    suspend fun registerMenu(
        name: String,
        price: Long,
        truckId: Long,
        picture: File?
    ): Result<String>

    suspend fun updateMenu(
        id: Long,
        name: String,
        price: Long,
        truckId: Long,
        picture: File?
    ): Result<String>

    suspend fun deleteMenu(
        id: Long
    ): Result<String>

}