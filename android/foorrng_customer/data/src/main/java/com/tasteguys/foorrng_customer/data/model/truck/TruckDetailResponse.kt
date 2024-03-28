package com.tasteguys.foorrng_customer.data.model.truck

import com.squareup.moshi.Json

data class TruckDetailResponse(
    @Json(name = "role")
    val type: String,
    @Json(name = "foodtruck")
    val mainInfo: TruckMainInfoResponse,
    @Json(name="review")
    val reviews: List<TruckReviewResponse>,
    @Json(name="menus")
    val menus: List<TruckMenuResponse>,
    @Json(name="totalReview")
    val totalReview: Int,
    @Json(name="mark")
    val operation: List<TruckDetailMarkResponse>

)