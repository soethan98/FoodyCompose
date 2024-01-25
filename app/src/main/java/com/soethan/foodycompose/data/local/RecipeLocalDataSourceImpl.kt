package com.soethan.foodycompose.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.soethan.foodcompose.database.RecipeDatabase
import com.soethan.foodycompose.database.FoodEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RecipeLocalDataSourceImpl @Inject constructor(private val recipeDatabase: RecipeDatabase) :
    RecipeLocalDataSource {


    override suspend fun addToFav(foodEntity: FoodEntity) {
        recipeDatabase.foodEntityQueries.addToFavorite(foodEntity)
    }

    override suspend fun removeFromFav(id: Int) {
        recipeDatabase.foodEntityQueries.removeFromFavorite(id.toLong())
    }

    override suspend fun getFavEntity(id: Int): Flow<FoodEntity> {
        return recipeDatabase.foodEntityQueries.getRecipeById(id.toLong()).asFlow()
            .mapToOne(Dispatchers.IO)
    }

    override suspend fun getAllFavRecipes(): Flow<List<FoodEntity>> {
        return recipeDatabase.foodEntityQueries.getAllFavRecipes().asFlow()
            .mapToList(Dispatchers.IO)
    }

}
