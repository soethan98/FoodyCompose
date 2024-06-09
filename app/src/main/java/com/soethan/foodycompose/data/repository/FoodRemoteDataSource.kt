package com.soethan.foodycompose.data.repository

import com.skydoves.sandwich.ApiResponse
import com.soethan.foodycompose.data.models.FoodJokeDto
import com.soethan.foodycompose.data.models.FoodRecipeDto
import com.soethan.foodycompose.data.models.RecipeDto

interface FoodRemoteDataSource {
    suspend fun getRandomRecipes(mealType:String?,dietType:String?): ApiResponse<FoodRecipeDto>
    suspend fun getRandomJokes(): ApiResponse<FoodJokeDto>

    suspend fun getRecipeDetail(id:String): ApiResponse<RecipeDto>
}


