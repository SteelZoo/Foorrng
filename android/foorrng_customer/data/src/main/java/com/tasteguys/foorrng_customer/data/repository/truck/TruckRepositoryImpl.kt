package com.tasteguys.foorrng_customer.data.repository.truck

import com.tasteguys.foorrng_customer.data.mapper.toData
import com.tasteguys.foorrng_customer.data.mapper.toDomain
import com.tasteguys.foorrng_customer.data.model.truck.TruckRegisterUpdateResponse
import com.tasteguys.foorrng_customer.data.repository.truck.remote.TruckRemoteDatasource
import com.tasteguys.foorrng_customer.domain.model.truck.FavoriteTruckData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckDetailData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckDetailMarkData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckOperationData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckRegisterUpdateData
import com.tasteguys.foorrng_customer.domain.repository.TruckRepository
import java.io.File
import javax.inject.Inject

private const val TAG = "TruckRepositoryImpl"

class TruckRepositoryImpl @Inject constructor(
    private val truckRemoteDatasource: TruckRemoteDatasource
) : TruckRepository {
    override suspend fun reportFoodTruck(
        name: String,
        picture: File?,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<TruckRegisterUpdateData> {
        return truckRemoteDatasource.reportFoodTruck(
            name, picture, carNumber, announcement, phoneNumber, category
        ).map { it.toDomain() }
    }

    override suspend fun updateFoodTruck(
        foodtruckId: Long,
        name: String,
        picture: File?,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ): Result<TruckRegisterUpdateData> {
        return truckRemoteDatasource.updateFoodTruck(
            foodtruckId, name, picture, carNumber, announcement, phoneNumber, category
        ).map { it.toDomain() }
    }

    override suspend fun getTruckList(
        latLeft: Double,
        lngLeft: Double,
        latRight: Double,
        lngRight: Double
    ): Result<List<TruckData>> {
        return truckRemoteDatasource.getTruckList(
            latLeft, lngLeft, latRight, lngRight
        ).map { it -> it.map { it.toDomain() } }
    }

    override suspend fun getTruckDetail(truckId: Long): Result<TruckDetailData> {
        return truckRemoteDatasource.getTruckDetail(truckId)
            .map { it.toDomain() }
    }

    override suspend fun markFavoriteTruck(truckId: Long): Result<Long> {
        return truckRemoteDatasource.markFavoriteTruck(truckId)
    }

    override suspend fun getFavoriteTruckList(): Result<List<FavoriteTruckData>> {
        return truckRemoteDatasource.getFavoriteTruckList().map { res ->
            res.map { it.toDomain() }
        }
    }

    override suspend fun registerTruckInfo(
        truckId: Long,
        address: String,
        lat: Double,
        lng: Double,
        operationInfo: List<TruckOperationData>
    ): Result<TruckDetailMarkData> {
        return truckRemoteDatasource.reportFoodTruckOperationInfo(truckId, address, lat, lng,
            operationInfo.map { it.toData() }
        ).map { it.toDomain() }
    }

}