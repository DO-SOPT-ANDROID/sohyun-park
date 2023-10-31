package org.sopt.dosopttemplate.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.repositoryimpl.AccountRepositoryImpl
import org.sopt.dosopttemplate.domain.repository.AccountRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsAccountRepository(
        RepositoryImpl: AccountRepositoryImpl,
    ): AccountRepository
}