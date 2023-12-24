package com.soethan.foodycompose.presentation.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.soethan.foodycompose.presentation.components.BottomBar
import com.soethan.foodycompose.presentation.navigation.FoodyNavigation
import com.soethan.foodycompose.presentation.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController: NavHostController = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(false)) }

    Scaffold(
        bottomBar = {
            BottomBar(navController, bottomBarState)
        }
    ) { padding ->
        FoodyNavigation(
            startDestination = Screens.RecipeList.route,
            bottomBarPadding = padding,
            bottomBarState = bottomBarState,
            navController = navController
        )
    }


}


