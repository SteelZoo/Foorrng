package com.tasteguys.foorrng_owner.data.model.foodtruck

data class FoodtruckRegistResponse(
    val foodtruckId: String,
    val announcement: String,
    val createdDay: String,
    val name: String,
    val accountInfo: String,
    val carNumber: String,
    val phoneNumber: String,
    val category: String,
    val picture: String
)