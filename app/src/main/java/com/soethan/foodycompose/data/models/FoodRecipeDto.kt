package com.soethan.foodycompose.data.models

import kotlinx.serialization.Serializable



@Serializable
data class FoodRecipeDto(
    val recipes: List<RecipeDto>
)