package com.tasteguys.foorrng_customer.module

import com.tasteguys.foorrng_customer.data.repository.user.remote.UserRemoteDatasource
import com.tasteguys.foorrng_customer.data.repository.user.remote.UserRemoteDatasourceImpl
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
}