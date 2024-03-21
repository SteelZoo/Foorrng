package com.tasteguys.foorrng_owner.presentation.model

data class FoodTruckInfo(
    val name: String,
    val businessNumber: String,
    val carNumber: String,
    val callNumber: String,
    val category: String,
    val notice: String,
    val reviewList: List<Review>,
)
