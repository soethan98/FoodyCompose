package com.soethan.foodycompose.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.soethan.foodycompose.R
import com.soethan.foodycompose.presentation.components.MainHeaderTitle
import com.soethan.foodycompose.presentation.components.ThemeChooserDropDown
import com.soethan.foodycompose.presentation.components.ThemeSwitcherMenu
import com.soethan.foodycompose.presentation.ui.LocalSnackBarHostState
import com.soethan.foodycompose.presentation.viewmodels.FoodJokeViewModel
import com.soethan.foodycompose.presentation.ui.theme.fontFamily
import com.soethan.foodycompose.presentation.viewmodels.MainViewModel
import com.soethan.foodycompose.utils.Resource
import com.soethan.foodycompose.utils.onError
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodJokeScreen(
    modifier: Modifier = Modifier,
    foodJokeViewModel: FoodJokeViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel(),
    onNavigateToSearch: () -> Unit
) {
    val result by foodJokeViewModel.randomJokeState.collectAsStateWithLifecycle()
    val appThemeMode by mainViewModel.appThemeState.collectAsStateWithLifecycle()
    val snackbarHostState = LocalSnackBarHostState.current

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = result) {
        result.onError { message ->
            scope.launch {
                snackbarHostState.showSnackbar(message)
            }
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.ic_food_joke_background),
                contentScale = ContentScale.FillBounds
            )
    ) {
        MainHeaderTitle(title = "Joke", actionMenu = {
            ThemeChooserDropDown(currentTheme = appThemeMode, onThemeChange = {
                mainViewModel.switchTheme(it)
            })
        })

        when (result) {
            is Resource.Loading -> CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )

            is Resource.Content ->
                AnimatedVisibility(
                    visible = true, Modifier
                        .align(Alignment.Center)
                ) {
                    Card(
                        modifier = Modifier
                            .padding(start = 24.dp, end = 24.dp)
                            .clip(RoundedCornerShape(6.dp)),
                        border = BorderStroke(width = 1.dp, color = Color.Gray.copy(alpha = 0.5f))

                    ) {
                        Text(
                            text = (result as Resource.Content).data,
                            fontSize = 20.sp,
                            fontFamily = fontFamily,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

            else -> Unit
        }


    }
}