package com.soethan.foodycompose.domain.repo

import com.soethan.foodycompose.domain.models.RecipeEntity

interface FoodRepo {

    suspend fun getRandomRecipes():List<RecipeEntity>
}