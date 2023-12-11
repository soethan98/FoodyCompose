package com.soethan.foodycompose.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun RecipeListScreen(
    modifier: Modifier = Modifier,
    recipeListViewModel: RecipeListViewModel = hiltViewModel()
) {
    recipeListViewModel.recipeListStateFlow.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "RecipeList Screen")
    }
}


