package com.soethan.foodycompose.data.repository

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.suspendOnSuccess
import com.soethan.foodycompose.data.models.FoodJokeDto
import com.soethan.foodycompose.data.models.FoodRecipeDto
import com.soethan.foodycompose.data.models.RecipeDto
import com.soethan.foodycompose.data.remote.SpoonacularClient
import javax.inject.Inject

class FoodRemoteDataSourceImpl @Inject constructor(
    private val spoonacularClient: SpoonacularClient
) : FoodRemoteDataSource {

    override suspend fun getRandomRecipes(): ApiResponse<FoodRecipeDto> {
      return spoonacularClient.getRandomRecipes()
    }

    override suspend fun getRandomJokes(): ApiResponse<FoodJokeDto> {
        return spoonacularClient.getRandomFoodJoke()
    }

    override suspend fun getRecipeDetail(id: String): ApiResponse<RecipeDto> {
        return spoonacularClient.getRecipeDetail(id)
    }

}