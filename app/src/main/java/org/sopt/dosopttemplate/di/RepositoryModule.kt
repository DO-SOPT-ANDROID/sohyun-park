package org.sopt.dosopttemplate.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.repositoryimpl.AuthRepositoryImpl
import org.sopt.dosopttemplate.data.repositoryimpl.HomeRepositoryImpl
import org.sopt.dosopttemplate.data.repositoryimpl.ReqresRepositoryImpl
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import org.sopt.dosopttemplate.domain.repository.HomeRepository
import org.sopt.dosopttemplate.domain.repository.ReqresRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsAccountRepository(
        RepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindsHomeRepository(
        RepositoryImpl: HomeRepositoryImpl,
    ): HomeRepository

    @Binds
    @Singleton
    abstract fun bindsReqresRepository(
        RepositoryImpl: ReqresRepositoryImpl,
    ): ReqresRepository
}