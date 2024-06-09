package com.soethan.foodycompose.data.repository

import com.skydoves.sandwich.map
import com.skydoves.sandwich.suspendMap
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.soethan.foodycompose.data.local.RecipeLocalDataSource
import com.soethan.foodycompose.data.mapper.ErrorResponseMapper
import com.soethan.foodycompose.data.mapper.RecipeLocalDataMapper
import com.soethan.foodycompose.data.models.toRecipeEntity
import com.soethan.foodycompose.domain.DataState
import com.soethan.foodycompose.domain.models.IngredientEntity
import com.soethan.foodycompose.domain.models.MealAndDietType
import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.domain.repo.FoodRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodRemoteDataSource: FoodRemoteDataSource,
    private val recipeLocalDataSource: RecipeLocalDataSource,
    private val recipeLocalDataMapper: RecipeLocalDataMapper
) :
    FoodRepo {

    override suspend fun getRandomRecipes(mealAndDietType: MealAndDietType): Flow<DataState<List<RecipeEntity>>> {
        return flow {
            val response = foodRemoteDataSource.getRandomRecipes(
                mealType = mealAndDietType.selectedMealType,
                dietType = mealAndDietType.selectedDietType
            )
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

    override suspend fun addToFav(recipe: RecipeEntity) {
        withContext(Dispatchers.IO) {
            recipeLocalDataSource.addToFav(recipeLocalDataMapper.mapFromRecipeEntity(recipe))
        }
    }

    override suspend fun removeFromFav(id: Int) {
        withContext(Dispatchers.IO) {
            recipeLocalDataSource.removeFromFav(id)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getFavRecipe(id: Int): Flow<RecipeEntity> {
        return recipeLocalDataSource.getFavEntity(id).mapLatest {
            recipeLocalDataMapper.mapToRecipeEntity(it)
        }.flowOn(Dispatchers.IO)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getAllFavRecipes(): Flow<DataState<List<RecipeEntity>>> {

        return recipeLocalDataSource.getAllFavRecipes().catch {
            DataState.Error(it.message, code = 1)
        }.mapLatest {
            val recipeEntities =
                it.map { recipeLocalDataMapper.mapToRecipeEntity(it).copy(isFavorite = true) }
            DataState.Success(recipeEntities)
        }
    }

    override suspend fun isFav(id: Int): Flow<Boolean> {
        return flow<Boolean> {
            val isFav = recipeLocalDataSource.isFavorite(id)
            emit(isFav)
        }.flowOn(Dispatchers.IO)

    }


}