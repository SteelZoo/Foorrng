package com.tasteguys.foorrng_customer.presentation.model.mapper

import com.tasteguys.foorrng_customer.domain.model.truck.FavoriteTruckData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckData
import com.tasteguys.foorrng_customer.domain.model.truck.TruckMenuData
import com.tasteguys.foorrng_customer.presentation.model.FavoriteTruck
import com.tasteguys.foorrng_customer.presentation.model.TruckDataWithAddress
import com.tasteguys.foorrng_customer.presentation.model.TruckMenu


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

fun FavoriteTruckData.toTruckDataWithAddress(): TruckDataWithAddress{
    return TruckDataWithAddress(
        truckId = id,
        markId = -1,
        name = name,
        picture = picture,
        isFavorite = true,
        reviewCnt,
        distance = -1,
        category = category,
        type = "FoodtruckReport",
        isOperating = false,
        ""
    )
}

fun FavoriteTruckData.toFavoriteTruck(): FavoriteTruck{
    return FavoriteTruck(
        id, name, picture, category, reviewCnt
    )
}

fun TruckMenuData.toTruckMenu(): TruckMenu{
    return TruckMenu(name, price.toInt(), picture)
}