package org.sopt.dosopttemplate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.domain.repository.AuthRepository
import org.sopt.dosopttemplate.domain.repository.HomeRepository
import org.sopt.dosopttemplate.domain.usecase.GetProfileUseCase
import org.sopt.dosopttemplate.domain.usecase.PostSignInUseCase
import org.sopt.dosopttemplate.domain.usecase.PostSignUpUseCase
import org.sopt.dosopttemplate.domain.usecase.SetUserInformationUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetProfileUseCase(repository: HomeRepository): GetProfileUseCase {
        return GetProfileUseCase(repository)
    }

    @Singleton
    @Provides
    fun providePostSignUpUseCase(repository: AuthRepository): PostSignUpUseCase {
        return PostSignUpUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSetUserInformationUseCase(repository: AuthRepository): SetUserInformationUseCase {
        return SetUserInformationUseCase(repository)
    }

    @Singleton
    @Provides
    fun providePostSignInUserCase(repository: AuthRepository): PostSignInUseCase {
        return PostSignInUseCase(repository)
    }
}