package com.soethan.foodycompose.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.math.BigDecimal

@Serializable
data class RecipeDto(
    val id: Int,
    val title: String?,
    val image: String?,
    val summary: String?,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val readyInMinutes: Int,
    val sourceName: String?,
    val sourceUrl: String,
    val glutenFree: Boolean,
    val dairyFree: Boolean,
    val cheap: Boolean,
    val healthScore: Double
)


