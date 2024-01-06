package com.soethan.foodycompose.domain.repo

import com.soethan.foodycompose.domain.DataState
import com.soethan.foodycompose.domain.models.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface FoodRepo {

    suspend fun getRandomRecipes(): Flow<DataState<List<RecipeEntity>>>


    suspend fun getRandomJoke(): Flow<DataState<String>>
    suspend fun getRecipeInformation(id: String): Flow<DataState<RecipeEntity>>
}