package com.tasteguys.foorrng_owner.domain.model.foodtruck

data class FoodtruckInfoData(
    val foodtruckId: Long,
    val announcement: String,
    val createdDay: Long,
    val name: String,
    val accountInfo: String,
    val carNumber: String,
    val phoneNumber: String,
    val category: String,
    val picture: String,
    val reviews: List<ReviewData>
)