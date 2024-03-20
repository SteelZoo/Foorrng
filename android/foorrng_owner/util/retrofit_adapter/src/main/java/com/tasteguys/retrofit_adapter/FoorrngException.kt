package com.tasteguys.retrofit_adapter

class FoorrngException(
    val code: Int,
    override val message: String
):Exception(message)