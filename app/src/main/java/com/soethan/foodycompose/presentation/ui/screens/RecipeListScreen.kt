package com.soethan.foodycompose.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.presentation.viewmodels.RecipeListViewModel
import com.soethan.foodycompose.presentation.components.RecipeCardItem
import com.soethan.foodycompose.utils.Resource
import com.soethan.foodycompose.utils.ShimmerList

@Composable
@ExperimentalMaterial3Api
fun RecipeListScreen(
    modifier: Modifier = Modifier,
    recipeListViewModel: RecipeListViewModel = hiltViewModel(),
    onNavigateToDetail: (Int) -> Unit

) {

    val recipeListState by recipeListViewModel.recipeListStateFlow.collectAsStateWithLifecycle()

    Surface(
        modifier = modifier
            .fillMaxSize()
    ) {


        when (recipeListState) {
            is Resource.Loading -> ShimmerList(length = 10)
            is Resource.Content -> RecipeContent(
                data = (recipeListState as Resource.Content<List<RecipeEntity>>).data,
                onNavigateToDetail = onNavigateToDetail
            )

            else -> Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No Data")
            }
        }
    }

}


@Composable
fun RecipeContent(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    data: List<RecipeEntity>,
    onNavigateToDetail: (Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        items(data.size) {
            RecipeCardItem(recipeEntity = data[it], onNavigateToDetail = {
                onNavigateToDetail(data[it].id)
            })
        }
    }
}
