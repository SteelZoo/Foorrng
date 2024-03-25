package com.tasteguys.foorrng_customer.data.model.truck

import retrofit2.http.Multipart

data class TruckRequest(
    val foodtruckId: Long,
    val name: String,
//    val picture: Multipart,
    val carNumber: String,
    val announcement: String,
    val accountInfo:String = "",
    val phoneNumber: String,
    val category: List<String>
){
    constructor(
        name: String,
//        picture: Multipart,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ):this(
        foodtruckId = -1,
        name = name,
//        picture = picture,
        carNumber = carNumber,
        announcement = announcement,
        phoneNumber = phoneNumber,
        category = category
    )

    constructor(
        truckId: Long,
        name: String,
//        picture: String,
        carNumber: String,
        announcement: String,
        phoneNumber: String,
        category: List<String>
    ):this(
        foodtruckId = truckId,
        name = name,
//        picture = picture,
        carNumber = carNumber,
        announcement = announcement,
        phoneNumber = phoneNumber,
        category = category
    )
}