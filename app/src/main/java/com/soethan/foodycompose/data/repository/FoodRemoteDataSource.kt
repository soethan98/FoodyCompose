package com.soethan.foodycompose.data.repository

import com.soethan.foodycompose.data.models.RecipeDto

interface FoodRemoteDataSource {
    suspend fun getRandomRecipes(): List<RecipeDto>
}