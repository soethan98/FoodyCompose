package com.soethan.foodycompose.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.presentation.ui.components.RecipeDetailBackDrop
import com.soethan.foodycompose.presentation.ui.components.RecipeDetailContent

@Composable
fun OverviewContent(modifier: Modifier = Modifier, recipeEntity: RecipeEntity) {
    Column(modifier = modifier, verticalArrangement = Arrangement.Top) {
        RecipeDetailBackDrop(recipeEntity = recipeEntity)


        RecipeDetailContent(recipeEntity = recipeEntity)


    }
}