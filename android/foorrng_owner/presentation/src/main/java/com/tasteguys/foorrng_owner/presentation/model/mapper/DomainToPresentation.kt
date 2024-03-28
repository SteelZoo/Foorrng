package com.tasteguys.foorrng_owner.presentation.model.mapper

import com.tasteguys.foorrng_owner.domain.model.foodtruck.FoodtruckInfoData
import com.tasteguys.foorrng_owner.domain.model.foodtruck.ReviewData
import com.tasteguys.foorrng_owner.presentation.model.foodtruck.FoodTruckInfo
import com.tasteguys.foorrng_owner.presentation.model.foodtruck.Review
import com.tasteguys.foorrng_owner.presentation.model.foodtruck.ReviewSet

fun FoodtruckInfoData.toFoodtruckInfo(): FoodTruckInfo{
    return FoodTruckInfo(
        id = foodtruckId,
        name = name,
        carNumber = carNumber,
        callNumber = phoneNumber,
        category = category,
        notice = announcement,
        reviewSet = reviews.toReviewSet()
    )
}

fun List<ReviewData>.toReviewSet(): ReviewSet {
    return ReviewSet(
        totalCount = maxOf { it.count },
        reviewList = map {
            Review(it.id, it.count)
        }
    )
}