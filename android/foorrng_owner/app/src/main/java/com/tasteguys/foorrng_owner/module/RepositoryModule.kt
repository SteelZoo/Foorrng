package com.tasteguys.foorrng_owner.module

import com.tasteguys.foorrng_owner.data.repository.user.UserRepositpryImpl
import com.tasteguys.foorrng_owner.domain.repository.UserRepository
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
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositpryImpl): UserRepository
}