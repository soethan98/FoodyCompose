package com.soethan.foodycompose.di

import android.content.Context
import com.soethan.foodycompose.data.local.UserSettings
import com.soethan.foodycompose.data.local.UserSettingsImpl
import com.soethan.foodycompose.data.remote.SpoonacularClient
import com.soethan.foodycompose.data.repository.FoodRemoteDataSource
import com.soethan.foodycompose.data.repository.FoodRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Singleton
    @Provides
    fun provideFoodRemoteDataSource(spoonacularClient: SpoonacularClient): FoodRemoteDataSource {
        return FoodRemoteDataSourceImpl(spoonacularClient)
    }

    @Singleton
    @Provides
    fun provideUserSettings(@ApplicationContext context: Context): UserSettings {
        return UserSettingsImpl(context)
    }


    @Singleton
    fun provideSpoonacularClient(): SpoonacularClient {
        return SpoonacularClient()
    }
}