package com.tasteguys.foorrng_owner.data.mapper

import com.tasteguys.foorrng_owner.data.model.foodtruck.FoodtruckDetailResponse
import com.tasteguys.foorrng_owner.data.model.foodtruck.ReviewResponse
import com.tasteguys.foorrng_owner.data.model.mark.MarkResponse
import com.tasteguys.foorrng_owner.data.model.mark.OperationInfoResponse
import com.tasteguys.foorrng_owner.data.model.menu.MenuResponse
import com.tasteguys.foorrng_owner.data.model.user.LoginResponse
import com.tasteguys.foorrng_owner.domain.model.foodtruck.FoodtruckInfoData
import com.tasteguys.foorrng_owner.domain.model.foodtruck.ReviewData
import com.tasteguys.foorrng_owner.domain.model.mark.MarkData
import com.tasteguys.foorrng_owner.domain.model.mark.OperationInfoData
import com.tasteguys.foorrng_owner.domain.model.menu.MenuData
import com.tasteguys.foorrng_owner.domain.model.user.LoginData

fun LoginResponse.toDomain() = LoginData(
    isBusiRegist = isBusiRegist,
    accessToken = accessToken,
    refreshToken = refreshToken
)

fun FoodtruckDetailResponse.toFoodtruckInfoData() = FoodtruckInfoData(
    foodtruckId = foodtruck.foodtruckId,
    announcement = foodtruck.announcement,
    createdDay = foodtruck.createdDay,
    name = foodtruck.name,
    accountInfo = foodtruck.accountInfo,
    carNumber = foodtruck.carNumber,
    phoneNumber = foodtruck.phoneNumber,
    category = foodtruck.category,
    picture = foodtruck.picture,
    totalReview = totalReview,
    reviews = review.map { it.toReviewData() },
    businessNumber = foodtruck.businessNumber ?: ""
)

fun ReviewResponse.toReviewData() = ReviewData(
    id,count.toInt()
)

fun MenuResponse.toMenuData() = MenuData(
    id = id,
    name = name,
    price = price,
    pictureUrl = pictureUrl,
    foodtruckId = foodtruckId
)

fun MarkResponse.toMarkData() = MarkData(
    id, latitude, longitude, address, isOpen, operationInfoList.map { it.toOperationInfoData() }
)

fun OperationInfoResponse.toOperationInfoData() = OperationInfoData(
    day, startTime, endTime
)