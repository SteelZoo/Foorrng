package com.tasteguys.foorrng_owner.presentation.model.foodtruck

data class FoodTruckInfo(
    val id: Long,
    val name: String,
    val carNumber: String,
    val callNumber: String,
    val category: String,
    val notice: String,
    val reviewSet: ReviewSet,
)
