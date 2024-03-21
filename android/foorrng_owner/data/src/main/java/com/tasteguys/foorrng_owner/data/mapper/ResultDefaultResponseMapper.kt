package com.tasteguys.foorrng_owner.data.mapper

import com.gdd.data.model.DefaultResponse
import com.tasteguys.foorrng_owner.data.model.DefaultResponse

fun <T>Result<DefaultResponse<T>>.toNonDefault(): Result<T> {
    return this.map {
        it.data
    }
}