package com.tasteguys.foorrng_owner.data.api

import com.tasteguys.foorrng_owner.data.model.DefaultResponse
import com.tasteguys.foorrng_owner.data.model.menu.MenuResponse
import okhttp3.MultipartBody
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part

interface MenuService {
    @POST("menu/regist")
    @Multipart
    suspend fun registMenu(
        @Part("menuRequestDto") name: String,
        @Part picture: MultipartBody.Part?,
    ) : Result<DefaultResponse<MenuResponse>>

    @PATCH("menu/{menuId}")
    @Multipart
    suspend fun updateMenu(
        @Part("menuRequestDto") name: String,
        @Part picture: MultipartBody.Part?,
    ): Result<DefaultResponse<MenuResponse>>

    @GET("menu/{foodtruckId}")
    suspend fun getMenuList(
        @Part("foodtruckId") foodtruckId: Long,
    ): Result<DefaultResponse<List<MenuResponse>>>
}