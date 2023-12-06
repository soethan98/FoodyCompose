package com.soethan.foodycompose.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class FoodRecipe(
    val recipes: List<Recipe>
)