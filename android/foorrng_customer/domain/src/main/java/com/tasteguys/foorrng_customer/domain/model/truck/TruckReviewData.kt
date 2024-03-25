package com.tasteguys.foorrng_customer.domain.model.truck

data class TruckReviewData(
    val id: Long,
    val isDelicious: Boolean,
    val isCool: Boolean,
    val isClean: Boolean,
    val isSpecial: Boolean,
    val isChip: Boolean,
    val isFast: Boolean,
    val createdDate: Long
)
