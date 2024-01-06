package com.soethan.foodycompose.data.repository

import com.skydoves.sandwich.map
import com.skydoves.sandwich.suspendMap
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.soethan.foodycompose.data.mapper.ErrorResponseMapper
import com.soethan.foodycompose.data.models.toRecipeEntity
import com.soethan.foodycompose.domain.DataState
import com.soethan.foodycompose.domain.models.IngredientEntity
import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.domain.repo.FoodRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(private val foodRemoteDataSource: FoodRemoteDataSource) :
    FoodRepo {

    override suspend fun getRandomRecipes(): Flow<DataState<List<RecipeEntity>>> {
        return flow {
            val response = foodRemoteDataSource.getRandomRecipes()
            response.suspendOnSuccess {
                val mappedData = this.data.recipes.map { it.toRecipeEntity() }
                emit(DataState.Success(mappedData))
            }.suspendOnError {
                suspendMap(ErrorResponseMapper) {
                    emit(DataState.Error(message, code))
                }
            }.suspendOnException {
                emit(DataState.Exception(throwable))
            }
        }
    }


    override suspend fun getRandomJoke(): Flow<DataState<String>> {
        return flow {
            val result = foodRemoteDataSource.getRandomJokes()
            result.suspendOnSuccess {
                emit(DataState.Success(data.text))
            }.suspendOnError {
                suspendMap(ErrorResponseMapper) {
                    emit(DataState.Error(message, code))
                }
            }.suspendOnException {
                emit(DataState.Exception(throwable))
            }
        }
    }

    override suspend fun getRecipeInformation(id: String): Flow<DataState<RecipeEntity>> {
        return flow {
            val result = foodRemoteDataSource.getRecipeDetail(id)
            result.suspendOnSuccess {
                emit(DataState.Success(data.toRecipeEntity()))
            }.suspendOnError {
                suspendMap(ErrorResponseMapper) {
                    emit(DataState.Error(message, code))
                }
            }.suspendOnException {
                emit(DataState.Exception(throwable))
            }
        }
    }



}