package com.soethan.foodycompose.domain.models

data class RecipeEntity(
    val id: Int,
    val title: String,
    val image: String,
    val summary: String?,
    val vegan: Boolean,
    val readyInMinutes: Int,
    val healthScore: Double
)