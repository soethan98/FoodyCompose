package com.soethan.foodycompose.presentation.navigation

sealed class Screens(val route: String) {
    object RecipeList : Screens("recipes")

    object FavoriteList : Screens("favorite_list")
    object FoodJoke : Screens("food_joke")

}