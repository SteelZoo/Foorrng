package com.tasteguys.foorrng_owner.presentation.model

import com.naver.maps.geometry.LatLng

data class RecommendLocation(
    val address: String,
    val comment: String,
    val latLng: LatLng
)