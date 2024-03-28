package com.tasteguys.foorrng_owner.data.model.foodtruck

import com.squareup.moshi.Json

data class ReviewResponse(
    @Json(name = "Id")
    val id: String,
    @Json(name = "cnt")
    val count: Long
)
