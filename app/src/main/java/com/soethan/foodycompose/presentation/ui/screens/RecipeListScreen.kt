package com.soethan.foodycompose.presentation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.presentation.components.MainHeaderTitle
import com.soethan.foodycompose.presentation.viewmodels.RecipeListViewModel
import com.soethan.foodycompose.presentation.components.RecipeCardItem
import com.soethan.foodycompose.presentation.components.SearchMenu
import com.soethan.foodycompose.utils.Resource
import com.soethan.foodycompose.utils.ShimmerList
import com.soethan.foodycompose.utils.onError
import kotlinx.coroutines.launch

@Composable
@ExperimentalMaterial3Api
fun RecipeListScreen(
    modifier: Modifier = Modifier,
    recipeListViewModel: RecipeListViewModel = hiltViewModel(),
    onNavigateToDetail: (Int) -> Unit,
    onNavigateToSearch: () -> Unit,
    snackbarHostState: SnackbarHostState
) {

    val scope = rememberCoroutineScope()

    val recipeListState by recipeListViewModel.recipeListStateFlow.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = recipeListState) {
        recipeListState.onError { message ->
            scope.launch {
                snackbarHostState.showSnackbar(message)
            }
        }
    }

    Surface(
        modifier = modifier
            .fillMaxSize()
    ) {

        Column(verticalArrangement = Arrangement.Top) {
            MainHeaderTitle(title = "Random", actionMenu = {
                SearchMenu(onNavigateToSearch)
            })
            when (recipeListState) {
                is Resource.Loading -> ShimmerList(length = 10)
                is Resource.Content -> RecipeContent(
                    data = (recipeListState as Resource.Content<List<RecipeEntity>>).data,
                    onNavigateToDetail = onNavigateToDetail
                )

                else -> Unit
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

