package org.sopt.dosopttemplate.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.sopt.dosopttemplate.features.util.AssetLoader
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AssetLoaderModule {
    @Provides
    @Singleton
    fun providesAssetLoader(@ApplicationContext context: Context): AssetLoader =
        AssetLoader(context)
}
