package com.tasteguys.foorrng_customer.module

import com.tasteguys.foorrng_customer.data.repository.truck.TruckRepositoryImpl
import com.tasteguys.foorrng_customer.data.repository.user.UserRepositoryImpl
import com.tasteguys.foorrng_customer.domain.repository.TruckRepository
import com.tasteguys.foorrng_customer.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Singleton
    @Binds
    abstract fun bindTruckRepository(truckRepositoryImpl: TruckRepositoryImpl): TruckRepository
}