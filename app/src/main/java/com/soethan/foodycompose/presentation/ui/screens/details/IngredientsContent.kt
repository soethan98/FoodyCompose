package com.soethan.foodycompose.presentation.ui.screens.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.soethan.foodycompose.domain.models.IngredientEntity
import com.soethan.foodycompose.presentation.components.IngredientCardItem

@Composable
fun IngredientsContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ingredientList: List<IngredientEntity>
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(ingredientList.size) {
            IngredientCardItem(ingredientEntity = ingredientList[it])
        }
    }

}