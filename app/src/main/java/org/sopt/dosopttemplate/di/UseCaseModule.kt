package org.sopt.dosopttemplate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.domain.repository.HomeRepository
import org.sopt.dosopttemplate.domain.usecase.GetProfileUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetProfileUseCase(repository: HomeRepository): GetProfileUseCase {
        return GetProfileUseCase(repository)
    }
}