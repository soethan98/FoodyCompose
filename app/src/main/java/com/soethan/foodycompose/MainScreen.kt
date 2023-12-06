package com.soethan.foodycompose

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {


    var selectedDestination by remember { mutableStateOf(MainRoute.RECEPES) }
    Scaffold(
        content = { pad ->
            Box(modifier = Modifier.padding(pad))
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_restaurant),
                    contentDescription = ""
                )
            }
        },
        bottomBar = {
            NavigationBar {
                TOP_LEVEL_DESTINATIONS.forEach { dest ->
                    NavigationBarItem(selected = dest.route == selectedDestination,
                        onClick = {
                            dest.route.also { selectedDestination = it }
                        },
                        icon = {
                            Icon(painter = painterResource(id = dest.icon), contentDescription = "")
                        },
                        label = {
                            Text(text = dest.route)
                        })
                }
            }
        }

    )
}


object MainRoute {
    const val RECEPES = "Recipes"
    const val FAVORITES = "Favorites"
    const val JOKES = "Food Jokes"
}


data class MainRouteDestination(
    val route: String,
    @DrawableRes
    val icon: Int
)

val TOP_LEVEL_DESTINATIONS = listOf(
    MainRouteDestination(
        route = MainRoute.RECEPES,
        icon = R.drawable.ic_menu_book
    ),
    MainRouteDestination(
        route = MainRoute.FAVORITES,
        icon = R.drawable.ic_heart
    ), MainRouteDestination(
        route = MainRoute.JOKES,
        icon = R.drawable.ic_food_joke
    )


)