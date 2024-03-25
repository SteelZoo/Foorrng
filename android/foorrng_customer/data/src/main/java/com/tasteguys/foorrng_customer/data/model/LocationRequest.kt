package com.tasteguys.foorrng_customer.data.model

import com.squareup.moshi.Json

data class LocationRequest(
    @Json(name="latitude_left")
    val latitudeLeft: Double,
    @Json(name="longitude_left")
    val longitudeLeft: Double,
    @Json(name="latitude_right")
    val latitudeRight: Double,
    @Json(name="longitude_right")
    val longitudeRight: Double,
)