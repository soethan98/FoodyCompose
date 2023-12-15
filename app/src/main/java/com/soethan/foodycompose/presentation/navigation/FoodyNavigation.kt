package com.soethan.foodycompose.presentation.navigation

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.soethan.foodycompose.presentation.FavoriteListScreen
import com.soethan.foodycompose.presentation.FoodJokeScreen
import com.soethan.foodycompose.presentation.RecipeDetailScreen
import com.soethan.foodycompose.presentation.RecipeListScreen
import com.soethan.foodycompose.utils.Constants

@Composable
fun FoodyNavigation(
    navController: NavHostController,
    startDestination: String,
    bottomBarPadding: PaddingValues,
    bottomBarState: MutableState<Boolean>
) {

    NavHost(
        navController = navController,
        startDestination = Screens.RecipeList.route,
        modifier = Modifier.padding(bottomBarPadding)
    ) {


        recipeListScreen(
            navController,
            bottomBarState = bottomBarState,
            bottomBarPadding = bottomBarPadding
        )
        favoriteListScreen(
            navController,
            bottomBarState = bottomBarState,
            bottomBarPadding = bottomBarPadding
        )
        foodJokeScreen(
            navController,
            bottomBarState = bottomBarState,
            bottomBarPadding = bottomBarPadding
        )
        recipeDetailScreen(
            navController
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.recipeListScreen(
    navController: NavController, bottomBarPadding: PaddingValues,
    bottomBarState: MutableState<Boolean>
) {
    composable(route = Screens.RecipeList.route) {
        bottomBarState.value = true
        val activity = (LocalContext.current as? Activity)
        BackHandler {
            activity?.finish()

        }
        RecipeListScreen(
        )
    }
}


fun NavGraphBuilder.favoriteListScreen(
    navController: NavController,
    bottomBarPadding: PaddingValues,
    bottomBarState: MutableState<Boolean>
) {
    composable(route = Screens.FavoriteList.route, enterTransition = {
        fadeIn(animationSpec = tween(200))
    },
        popExitTransition = { fadeOut(animationSpec = tween(200)) },
        exitTransition = { fadeOut(animationSpec = tween(200)) }
    ) {
        bottomBarState.value = true
        FavoriteListScreen()
    }
}

fun NavGraphBuilder.foodJokeScreen(
    navController: NavHostController,
    bottomBarPadding: PaddingValues,
    bottomBarState: MutableState<Boolean>
) {
    composable(route = Screens.FoodJoke.route, enterTransition = {
        fadeIn(animationSpec = tween(200))
    },
        popExitTransition = { fadeOut(animationSpec = tween(200)) },
        exitTransition = { fadeOut(animationSpec = tween(200)) }
    ) {
        bottomBarState.value = true
        FoodJokeScreen {
            navController.navigate("recipe_detail/123")
        }
    }
}

fun NavGraphBuilder.recipeDetailScreen(
    navController: NavController
) {
    composable(
        route = Screens.RecipeDetail.route,
        arguments = listOf(navArgument(Constants.RECIPE_DETAIL_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) {
        RecipeDetailScreen()
    }
}
