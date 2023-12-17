package com.soethan.foodycompose.domain.models

data class RecipeEntity(
    val id: Int,
    val title: String,
    val image: String,
    val summary: String?,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val glutenFree: Boolean,
    val dairyFree: Boolean,
    val cheap: Boolean,
    val readyInMinutes: Int,
    val healthScore: Double,
    val ingredients: List<IngredientEntity>
)