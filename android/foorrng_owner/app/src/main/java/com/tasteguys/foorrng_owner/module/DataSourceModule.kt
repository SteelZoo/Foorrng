package com.tasteguys.foorrng_owner.module

import com.tasteguys.foorrng_owner.data.repository.foodtruck.remote.FoodtruckRemoteDatasource
import com.tasteguys.foorrng_owner.data.repository.foodtruck.remote.FoodtruckRemoteDatasourceImpl
import com.tasteguys.foorrng_owner.data.repository.menu.remote.MenuRemoteDataSource
import com.tasteguys.foorrng_owner.data.repository.menu.remote.MenuRemoteDataSourceImpl
import com.tasteguys.foorrng_owner.data.repository.user.remote.UserRemoteDatasource
import com.tasteguys.foorrng_owner.data.repository.user.remote.UserRemoteDatasourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
    abstract fun bindUserRemoteDataSource(userRemoteDataSourceImpl: UserRemoteDatasourceImpl): UserRemoteDatasource

    @Singleton
    @Binds
    abstract fun bindFoodtruckRemoteDataSource(foodtruckRemoteDataSourceImpl: FoodtruckRemoteDatasourceImpl): FoodtruckRemoteDatasource

    @Singleton
    @Binds
    abstract fun bindMenuRemoteDataSource(menuRemoteDataSourceImpl: MenuRemoteDataSourceImpl): MenuRemoteDataSource
}