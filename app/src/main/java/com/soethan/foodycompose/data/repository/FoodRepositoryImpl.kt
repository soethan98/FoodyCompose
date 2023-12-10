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
                title = it.title,
                image = it.image

            )
        }
    }

}