package com.tasteguys.foorrng_owner.presentation.model.mapper

import com.tasteguys.foorrng_owner.domain.model.foodtruck.FoodtruckInfoData
import com.tasteguys.foorrng_owner.domain.model.foodtruck.ReviewData
import com.tasteguys.foorrng_owner.domain.model.menu.MenuData
import com.tasteguys.foorrng_owner.presentation.model.foodtruck.FoodTruckInfo
import com.tasteguys.foorrng_owner.presentation.model.foodtruck.Menu
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
        pictureUrl = picture,
        accountInfo = accountInfo,
        reviewSet = reviews.toReviewSet(totalReview)
    )
}

fun List<ReviewData>.toReviewSet(totalReview: Int = -1): ReviewSet {
    return ReviewSet(
        totalCount = if(totalReview < 0) maxOf { it.count } else totalReview,
        reviewList = map {
            Review(it.id, it.count)
        }
    )
}

fun MenuData.toMenu(): Menu {
    return Menu(
        id,pictureUrl,name,price.toInt()
    )
}