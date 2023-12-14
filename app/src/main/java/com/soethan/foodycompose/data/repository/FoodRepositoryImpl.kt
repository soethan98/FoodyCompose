package com.soethan.foodycompose.data.repository

import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.domain.repo.FoodRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
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
                readyInMinutes = it.readyInMinutes,)
        }
    }

    override suspend fun getRandomJoke(): Flow<String> {
        return flow<String> {
            val result = foodRemoteDataSource.getRandomJokes()
            emit(result.text)
        }
    }

}