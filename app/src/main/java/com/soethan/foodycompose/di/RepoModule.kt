package com.soethan.foodycompose.di

import com.soethan.foodycompose.data.repository.FoodRepositoryImpl
import com.soethan.foodycompose.domain.repo.FoodRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {
    @Binds
    @Singleton
    abstract fun bindFoodRepo(
        foodRepositoryImpl: FoodRepositoryImpl
    ): FoodRepo
}