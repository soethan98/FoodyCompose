package com.soethan.foodycompose.data.repository

import com.soethan.foodycompose.data.models.FoodJokeDto
import com.soethan.foodycompose.data.models.RecipeDto
import com.soethan.foodycompose.data.remote.SpoonacularClient
import javax.inject.Inject

class FoodRemoteDataSourceImpl @Inject constructor(
    private val spoonacularClient: SpoonacularClient
): FoodRemoteDataSource{
    override suspend fun getRandomRecipes(): List<RecipeDto> {
        return spoonacularClient.getRandomRecipes().recipes
    }

    override suspend fun getRandomJokes(): FoodJokeDto {
        return spoonacularClient.getRandomFoodJoke()
    }

}