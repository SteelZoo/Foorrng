package com.tasteguys.foorrng_customer.data.model.truck

data class TruckMainInfoResponse(
    val announcement: String,
    val name: String,
    val createdDay: Long,
    val picture: String,
    val accountInfo: String,
    val carNumber: String,
    val phoneNumber: String,

    val bussiNumber: String,
    val category: List<String>

)