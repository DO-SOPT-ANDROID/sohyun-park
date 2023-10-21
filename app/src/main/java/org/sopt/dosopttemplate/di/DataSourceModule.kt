package org.sopt.dosopttemplate.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.data.datasource.SharedPreferenceDataSource
import org.sopt.dosopttemplate.data_local.datasourceimpl.SharedPreferencesDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun providesSharedPreferenceDataSource(DataSourceImpl: SharedPreferencesDataSourceImpl): SharedPreferenceDataSource
}