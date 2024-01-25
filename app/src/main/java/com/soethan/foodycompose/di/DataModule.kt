package com.soethan.foodycompose.di

import android.content.Context
import android.util.Log
import androidx.sqlite.db.SupportSQLiteOpenHelper
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import app.cash.sqldelight.logs.LogSqliteDriver
import com.soethan.foodcompose.database.RecipeDatabase
import com.soethan.foodycompose.BuildConfig
import com.soethan.foodycompose.data.local.RecipeLocalDataSource
import com.soethan.foodycompose.data.local.RecipeLocalDataSourceImpl
import com.soethan.foodycompose.data.local.UserSettings
import com.soethan.foodycompose.data.local.UserSettingsImpl
import com.soethan.foodycompose.data.local.ingredientsAdapter
import com.soethan.foodycompose.data.remote.SpoonacularClient
import com.soethan.foodycompose.data.repository.FoodRemoteDataSource
import com.soethan.foodycompose.data.repository.FoodRemoteDataSourceImpl
import com.soethan.foodycompose.database.FoodEntity
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
    fun provideRecipeLocalDataSource(recipeDatabase: RecipeDatabase):RecipeLocalDataSource{
        return RecipeLocalDataSourceImpl(recipeDatabase)
    }

    @Singleton
    @Provides
    fun provideUserSettings(@ApplicationContext context: Context): UserSettings {
        return UserSettingsImpl(context)
    }


    @Singleton
    @Provides
    fun initSqlDriver(@ApplicationContext applicationContext: Context): SqlDriver {
        val androidSqliteDriver =
            AndroidSqliteDriver(RecipeDatabase.Schema, context = applicationContext, "recipe_db")
        return if (BuildConfig.DEBUG) {
            LogSqliteDriver(androidSqliteDriver) { Log.d("db", it) }
        } else {
            androidSqliteDriver
        }

    }

    @Singleton
    @Provides
    fun provideSqlDriver(driver: SqlDriver): RecipeDatabase {
        return RecipeDatabase(driver,FoodEntity.Adapter(
            ingredientsAdapter = ingredientsAdapter
        ))
    }


    @Singleton
    @Provides
    fun provideSpoonacularClient(): SpoonacularClient {
        return SpoonacularClient()
    }
}