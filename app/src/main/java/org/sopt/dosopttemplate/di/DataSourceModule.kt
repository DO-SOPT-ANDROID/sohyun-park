package org.sopt.dosopttemplate.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.datasource.AuthDataSource
import org.sopt.dosopttemplate.data.datasource.HomeDataSource
import org.sopt.dosopttemplate.data.datasource.ReqresDataSource
import org.sopt.dosopttemplate.data.datasource.SharedPreferenceDataSource
import org.sopt.dosopttemplate.data_local.datasourceimpl.HomeDataSourceImpl
import org.sopt.dosopttemplate.data_local.datasourceimpl.SharedPreferencesDataSourceImpl
import org.sopt.dosopttemplate.data_remote.datasourceimpl.AuthDataSourceImpl
import org.sopt.dosopttemplate.data_remote.datasourceimpl.ReqresDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun providesSharedPreferenceDataSource(DataSourceImpl: SharedPreferencesDataSourceImpl): SharedPreferenceDataSource

    @Singleton
    @Binds
    abstract fun providesHomeDataSource(DataSourceImpl: HomeDataSourceImpl): HomeDataSource

    @Singleton
    @Binds
    abstract fun providesAuthDataSource(DataSourceImpl: AuthDataSourceImpl): AuthDataSource

    @Singleton
    @Binds
    abstract fun providesReqresDataSource(DataSourceImpl: ReqresDataSourceImpl): ReqresDataSource
}