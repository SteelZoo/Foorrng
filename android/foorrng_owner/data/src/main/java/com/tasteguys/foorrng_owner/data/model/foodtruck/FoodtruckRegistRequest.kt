package com.tasteguys.foorrng_owner.data.model.foodtruck

data class FoodtruckRegistRequest(
    val name: String,
    val carNumber: String,
    val accountInfo: String,
    val phoneNumber: String,
    val announcement: String,
    val category: List<String>,
)
