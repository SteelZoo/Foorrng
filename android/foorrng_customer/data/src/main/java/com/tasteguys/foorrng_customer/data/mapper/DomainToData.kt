package com.tasteguys.foorrng_customer.data.mapper

import com.tasteguys.foorrng_customer.data.model.truck.TruckDetailResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckListResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckMainInfoResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckMenuResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckOperationResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckReviewResponse
import com.tasteguys.foorrng_customer.data.model.user.LoginResponse
import com.tasteguys.foorrng_customer.domain.model.truck.TruckData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckDetailData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckMainData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckMenuData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckOperationData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckReviewData
import com.tasteguys.foorrng_customer.domain.model.user.TokenData

fun LoginResponse.toDomain() = TokenData(
    accessToken = accessToken,
    refreshToken = refreshToken
)

fun TruckListResponse.toDomain() = TruckData(
    truckId = truckId,
    markId = markId,
    latitude = latitude,
    longitude = longitude,
    name = name,
    picture = picture,
    type = type,
    category = category,
    reviewCnt = reviewCnt,
    favorite = favorite,
    isOperating = isOperating
)

fun TruckDetailResponse.toDomain() = TruckDetailData(
    type,
    mainInfo.toDomain(),
    reviews.map { it.toDomain() },
    menus.map { it.toDomain() },
    operation.map { it.toDomain() }
)

fun TruckMainInfoResponse.toDomain() = TruckMainData(
    announcement, name, createdDay, picture, accountInfo, carNumber, phoneNumber, bussiNumber, category
)

fun TruckReviewResponse.toDomain() = TruckReviewData(
    id, isDelicious, isCool, isClean, isSpecial, isChip, isFast, createdDate
)

fun TruckMenuResponse.toDomain() = TruckMenuData(
    id, name, price, picture
)

fun TruckOperationResponse.toDomain() = TruckOperationData(
    id, day, startTime, endTime
)