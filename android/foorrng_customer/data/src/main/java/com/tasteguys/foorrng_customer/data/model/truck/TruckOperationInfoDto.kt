package com.tasteguys.foorrng_customer.data.model.truck

import com.squareup.moshi.Json

data class TruckOperationInfoDto(
    val operationInfoList: List<TruckOperationInfo> = listOf()
)