package com.tasteguys.foorrng_customer.data.mapper

import com.tasteguys.foorrng_customer.data.model.truck.TruckListResponse
import com.tasteguys.foorrng_customer.data.model.user.LoginResponse
import com.tasteguys.foorrng_customer.domain.model.truck.TruckData
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