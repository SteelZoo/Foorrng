package com.tasteguys.foorrng_customer.presentation.model

data class TruckWithFavorite(
    val truckId: Long,
    val userId: Long,
    val name: String,
    val picture: String,
    val isFavorite: Boolean,
    val numOfReview: Int,
    val distance: Int,
    val category: List<String>
)
