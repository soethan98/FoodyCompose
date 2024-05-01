package com.soethan.foodycompose.data.models

import com.soethan.foodycompose.domain.models.RecipeEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable()
data class RecipeDto(
    val id: Int,
    val title: String?,
    val image: String = "",
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
    val healthScore: Double,
    @SerialName(value = "extendedIngredients")
    val ingredients: List<IngredientDto>
)


fun RecipeDto.toRecipeEntity():RecipeEntity {
   return RecipeEntity(
        id = this.id,
        cheap = this.cheap ?: false,
        title = this.title ?: "",
        image = this.image ?: "",
        summary = this.summary ?: "",
        vegan = this.vegan,
        vegetarian = this.vegetarian,
        veryHealthy = this.veryHealthy,
        glutenFree = this.glutenFree,
        dairyFree = this.dairyFree,
        healthScore = this.healthScore ?: 0.0,
        readyInMinutes = this.readyInMinutes,
       sourceUrl = this.sourceUrl,
        ingredients = this.ingredients.map{ it.toIngredientEntity() }
    )
}