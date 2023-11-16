package org.sopt.dosopttemplate.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data_remote.api.AuthApiService
import org.sopt.dosopttemplate.data_remote.api.ReqresApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideSignApiService(@DoSoptAuthRetrofit retrofit: Retrofit): AuthApiService =
        retrofit.create(AuthApiService::class.java)

    @Provides
    @Singleton
    fun provideReqresApiService(@ReqresRetrofit retrofit: Retrofit): ReqresApiService =
        retrofit.create(ReqresApiService::class.java)
}