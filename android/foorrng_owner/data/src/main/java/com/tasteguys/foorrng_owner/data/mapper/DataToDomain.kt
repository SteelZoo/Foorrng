package com.tasteguys.foorrng_owner.data.mapper

import com.tasteguys.foorrng_owner.data.model.foodtruck.FoodtruckDetailResponse
import com.tasteguys.foorrng_owner.data.model.foodtruck.ReviewResponse
import com.tasteguys.foorrng_owner.data.model.user.LoginResponse
import com.tasteguys.foorrng_owner.domain.model.foodtruck.FoodtruckInfoData
import com.tasteguys.foorrng_owner.domain.model.foodtruck.ReviewData
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
    reviews = review.map { it.toReviewData() }
)

fun ReviewResponse.toReviewData() = ReviewData(
    id,count.toInt()
)