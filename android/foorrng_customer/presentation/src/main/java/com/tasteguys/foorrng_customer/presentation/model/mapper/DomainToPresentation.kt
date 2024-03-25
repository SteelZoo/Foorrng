package com.tasteguys.foorrng_customer.presentation.model.mapper

import com.tasteguys.foorrng_customer.domain.model.truck.TruckData
import com.tasteguys.foorrng_customer.presentation.model.TruckDataWithAddress



fun TruckData.toTruckDataWithAddress(): TruckDataWithAddress{
    return TruckDataWithAddress(
        truckId,
        markId,
        name,
        picture,
        favorite,
        reviewCnt,
        // distance 구하는 함수 필요
        17,
        category,
        type,
        isOperating,
        //구하는 함수 필요
        "ㅇㄹㅇㄹ"
    )
}