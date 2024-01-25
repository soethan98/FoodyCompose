package com.soethan.foodycompose.data.mapper

import com.soethan.foodycompose.data.models.IngredientDto
import com.soethan.foodycompose.data.models.toIngredientEntity
import com.soethan.foodycompose.database.FoodEntity
import com.soethan.foodycompose.domain.models.IngredientEntity
import com.soethan.foodycompose.domain.models.RecipeEntity

class RecipeLocalDataMapper {

    fun mapFromRecipeEntity(recipeEntity: RecipeEntity): FoodEntity {
        return FoodEntity(
            id = recipeEntity.id.toLong(),
            title = recipeEntity.title,
            cheap = recipeEntity.cheap,
            glutenFree = recipeEntity.glutenFree,
            healthScore = recipeEntity.healthScore,
            image = recipeEntity.image,
            readyInMinutes = recipeEntity.readyInMinutes.toLong(),
            sourceUrl = recipeEntity.sourceUrl,
            summary = recipeEntity.summary ?: "",
            vegan = recipeEntity.vegan,
            veryHealthy = recipeEntity.veryHealthy,
            diaryFree = recipeEntity.dairyFree,
            vegetarian = recipeEntity.vegetarian,
            ingredients = recipeEntity.ingredients.map {
                IngredientDto(
                    aisle = it.aisle,
                    amount = it.amount,
                    consistency = it.consistency,
                    id = it.id,
                    name = it.name ?: "",
                    image = it.image ?: "",
                    originalName = it.originalName,
                    original = it.original,
                    unit = it.unit
                )
            }
        )
    }


    fun mapToRecipeEntity(foodEntity: FoodEntity): RecipeEntity {
        return RecipeEntity(
            id = foodEntity.id.toInt(),
            title = foodEntity.title,
            cheap = foodEntity.cheap ?: false,
            dairyFree = foodEntity.diaryFree ?: false,
            glutenFree = foodEntity.glutenFree ?: false,
            healthScore = foodEntity.healthScore ?: 0.0,
            image = foodEntity.image,
            readyInMinutes = foodEntity.readyInMinutes?.toInt() ?: 0,
            sourceUrl = foodEntity.sourceUrl,
            summary = foodEntity.summary,
            vegan = foodEntity.vegan ?: false,
            veryHealthy = foodEntity.veryHealthy ?: false,
            vegetarian = foodEntity.veryHealthy ?: false,
            ingredients = foodEntity.ingredients.map {
                IngredientEntity(
                    aisle = it.aisle ?: "",
                    amount = it.amount ?: 0.0,
                    consistency = it.consistency ?: "",
                    id = it.id,
                    name = it.name ?: "",
                    image = it.image ?: "",
                    originalName = it.originalName ?: "",
                    original = it.original ?: "",
                    unit = it.unit ?: ""
                )
            } ?: listOf()
        )
    }

}