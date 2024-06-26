package com.soethan.foodycompose.domain.repo

import android.provider.ContactsContract.Data
import com.soethan.foodycompose.domain.DataState
import com.soethan.foodycompose.domain.models.MealAndDietType
import com.soethan.foodycompose.domain.models.RecipeEntity
import kotlinx.coroutines.flow.Flow

interface FoodRepo {

    suspend fun getRandomRecipes(mealAndDietType: MealAndDietType): Flow<DataState<List<RecipeEntity>>>
    suspend fun getRandomJoke(): Flow<DataState<String>>
    suspend fun getRecipeInformation(id: String): Flow<DataState<RecipeEntity>>

    suspend fun addToFav(recipe: RecipeEntity)
    suspend fun removeFromFav(id: Int)

    suspend fun getFavRecipe(id: Int): Flow<RecipeEntity>

    suspend fun getAllFavRecipes(): Flow<DataState<List<RecipeEntity>>>

    suspend fun isFav(id:Int):Flow<Boolean>
}