package com.soethan.foodycompose.data.repository

import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.domain.repo.FoodRepo
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(private val foodRemoteDataSource: FoodRemoteDataSource) :
    FoodRepo {
    override suspend fun getRandomRecipes(): List<RecipeEntity> {

        return foodRemoteDataSource.getRandomRecipes().map {
            RecipeEntity(
                id = it.id,
                title = it.title ?: "",
                summary = it.summary ?: "",
                image = it.image ?: "",

                healthScore = it.healthScore,
                vegan = it.vegan,
                readyInMinutes = it.readyInMinutes,


                )
        }
    }

}