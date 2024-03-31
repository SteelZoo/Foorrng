package com.tasteguys.foorrng_customer.data.api

import com.tasteguys.foorrng_customer.data.model.DefaultResponse
import com.tasteguys.foorrng_customer.data.model.LocationRequest
import com.tasteguys.foorrng_customer.data.model.truck.TruckRegisterUpdateResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckDetailResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckFavoriteListResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckListResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckMarkRequest
import com.tasteguys.foorrng_customer.data.model.truck.TruckMenuRequest
import com.tasteguys.foorrng_customer.data.model.truck.TruckMenuResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckRegisterOperationResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckRegisterReviewRequest
import com.tasteguys.foorrng_customer.data.model.truck.TruckRegisterReviewResponse
import com.tasteguys.foorrng_customer.data.model.truck.TruckRequest
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path

interface FoodTruckService {
    @Multipart
    @POST("foodtruck-report/regist")
    suspend fun reportFoodTruck(
        @Part("foodtruckDto") truckRequest: TruckRequest,
        @Part picture: MultipartBody.Part?
    ): Result<DefaultResponse<TruckRegisterUpdateResponse>>

    @Multipart
    @PATCH("foodtruck-report/update")
    suspend fun updateFoodTruck(
        @Part("foodtruckDto") truckRequest: TruckRequest,
        @Part picture: MultipartBody.Part?,
    ): Result<DefaultResponse<TruckRegisterUpdateResponse>>

    @POST("foodtrucks")
    suspend fun getTrucks(
        @Body location: LocationRequest
    ): Result<DefaultResponse<List<TruckListResponse>>>

    @GET("foodtrucks/detail/{foodtruckId}")
    suspend fun getTruckDetail(
        @Path("foodtruckId") truckId: Long
    ): Result<DefaultResponse<TruckDetailResponse>>

    @POST("foodtrucks/like/{foodtruckId}")
    suspend fun markFavoriteFoodTruck(
        @Path("foodtruckId") truckId: Long
    ): Result<DefaultResponse<String>>

    @GET("mypage/likes")
    suspend fun getFavoriteTrucks(): Result<DefaultResponse<List<TruckFavoriteListResponse>>>

    @POST("mark/{foodtruck-id}/regist")
    suspend fun registerMark(
        @Path("foodtruck-id") truckId: Long,
        @Body markInfo: TruckMarkRequest
    ): Result<DefaultResponse<TruckRegisterOperationResponse>>

    @POST("review/{foodtruckId}/regist")
    suspend fun registerReview(
        @Path("foodtruckId") truckId: Long,
        @Body review: TruckRegisterReviewRequest
    ): Result<DefaultResponse<TruckRegisterReviewResponse>>

    @GET("menu/{foodtruckId}")
    suspend fun getMenu(@Path("foodtruckId") truckId: Long): Result<DefaultResponse<List<TruckMenuResponse>>>

    @POST("menu/regist")
    suspend fun registerMenu(
        @Part("menuRequestDto") menuRequest: TruckMenuRequest,
        @Part picture: MultipartBody.Part?
    ):Result<DefaultResponse<String>>

    @PATCH("menu/{menuId}")
    suspend fun updateMenu(
        @Path("menuId") id: Long,
        @Part("menuRequestDto") menuRequest: TruckMenuRequest,
        @Part picture: MultipartBody.Part?
    ): Result<DefaultResponse<String>>
    @DELETE("menu/{menuId}")
    suspend fun deleteMenu(@Path("menuId") id: Long): Result<DefaultResponse<String>>



}