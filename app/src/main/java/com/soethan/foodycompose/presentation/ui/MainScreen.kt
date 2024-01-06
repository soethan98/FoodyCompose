package com.soethan.foodycompose.presentation.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

    val snackbarHostState = remember { SnackbarHostState() }


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        bottomBar = {
            BottomBar(navController, bottomBarState)
        }
    ) { padding ->
        FoodyNavigation(
            startDestination = Screens.RecipeList.route,
            bottomBarPadding = padding,
            bottomBarState = bottomBarState,
            navController = navController,
            snackbarState = snackbarHostState
        )
    }


}


