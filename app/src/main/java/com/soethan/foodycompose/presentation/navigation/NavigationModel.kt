package com.soethan.foodycompose.presentation.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.soethan.foodycompose.R

sealed class NavigationModel(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    object RecipeList : NavigationModel(
        route = Screens.RecipeList.route, title = R.string.recipes,
        icon = R.drawable.ic_menu_book
    )
    object Favorite : NavigationModel(
        route = Screens.FavoriteList.route, title = R.string.favorite_recipes,
        icon = R.drawable.ic_heart
    )
    object FoodJoke : NavigationModel(
        route = Screens.FoodJoke.route, title = R.string.food_joke,
        icon = R.drawable.ic_food_joke
    )
}

