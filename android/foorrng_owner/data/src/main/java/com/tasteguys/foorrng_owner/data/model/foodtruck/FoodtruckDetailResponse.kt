package com.tasteguys.foorrng_owner.data.model.foodtruck

data class FoodtruckDetailResponse(
    val foodtruck: FoodtruckRegistResponse,
    val reviews: List<ReviewResponse>
)