package com.tasteguys.foorrng_owner.presentation.model.location

import com.naver.maps.geometry.LatLng

data class RunLocationInfo(
    val address: String,
    val latLng: LatLng,
    val runInfoList: List<RunInfo>,
)
