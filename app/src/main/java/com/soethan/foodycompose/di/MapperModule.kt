package com.soethan.foodycompose.di

import com.soethan.foodycompose.data.mapper.RecipeLocalDataMapper
import com.soethan.foodycompose.data.remote.SpoonacularClient
import com.soethan.foodycompose.data.repository.FoodRemoteDataSource
import com.soethan.foodycompose.data.repository.FoodRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class MapperModule {
    @Singleton
    @Provides
    fun provideLocalDataMapper(): RecipeLocalDataMapper {
        return RecipeLocalDataMapper()
    }
}