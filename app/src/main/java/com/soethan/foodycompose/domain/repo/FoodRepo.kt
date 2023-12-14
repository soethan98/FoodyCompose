package com.soethan.foodycompose.domain.repo

import com.soethan.foodycompose.domain.models.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface FoodRepo {

    suspend fun getRandomRecipes():List<RecipeEntity>


   suspend fun getRandomJoke():Flow<String>
}