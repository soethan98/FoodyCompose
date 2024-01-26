package com.soethan.foodycompose.data.local

import com.soethan.foodycompose.database.FoodEntity
import kotlinx.coroutines.flow.Flow


interface RecipeLocalDataSource{
    suspend fun addToFav(foodEntity: FoodEntity)
    suspend fun removeFromFav(id:Int)
    suspend fun getFavEntity(id:Int): Flow<FoodEntity>

    suspend fun getAllFavRecipes():Flow<List<FoodEntity>>

    suspend fun isFavorite(id:Int):Boolean
}