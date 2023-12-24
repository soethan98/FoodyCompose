package com.soethan.foodycompose.presentation.ui.screens.details

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.presentation.components.RecipeDetailBackDrop
import com.soethan.foodycompose.presentation.components.RecipeDetailContent

@Composable
fun OverviewContent(modifier: Modifier = Modifier, recipeEntity: RecipeEntity) {
    LazyColumn{
        item {
            RecipeDetailBackDrop(recipeEntity = recipeEntity)
        }
        item {
            RecipeDetailContent(recipeEntity = recipeEntity)
        }
    }

}