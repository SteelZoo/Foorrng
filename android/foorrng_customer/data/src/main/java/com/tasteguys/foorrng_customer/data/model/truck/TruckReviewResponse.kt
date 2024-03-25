package com.tasteguys.foorrng_customer.data.model.truck

import com.squareup.moshi.Json

data class TruckReviewResponse(
    val id: Long,
    @Json(name = "rvIsDelicious")
    val isDelicious: Boolean,
    @Json(name = "isCool")
    val isCool: Boolean,
    @Json(name = "isClean")
    val isClean: Boolean,
    @Json(name = "rvIdSpecial")
    val isSpecial: Boolean,
    @Json(name = "rvIsChip")
    val isChip: Boolean,
    @Json(name = "rvIsFast")
    val isFast: Boolean,
    val createdDate: Long
)