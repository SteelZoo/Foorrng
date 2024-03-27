package com.tasteguys.foorrng_customer.data.mapper

import com.tasteguys.foorrng_customer.data.model.truck.TruckOperationInfo
import com.tasteguys.foorrng_customer.domain.model.truck.TruckOperationData

fun TruckOperationData.toData() = TruckOperationInfo(
    day, startTime, endTime
)