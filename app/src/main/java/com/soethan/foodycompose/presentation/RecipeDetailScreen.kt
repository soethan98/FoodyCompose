package com.soethan.foodycompose.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.soethan.foodycompose.R
import com.soethan.foodycompose.presentation.ui.IngredientsContent
import com.soethan.foodycompose.presentation.ui.OverviewContent
import com.soethan.foodycompose.presentation.ui.components.RecipeDetailTabTabRow
import com.soethan.foodycompose.utils.Resource

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun RecipeDetailScreen(
    modifier: Modifier = Modifier,
    recipeDetailViewModel: RecipeDetailViewModel = hiltViewModel(),
    onPopPage: () -> Unit
) {
    val tabs = listOf(
        stringResource(id = R.string.overview),
        stringResource(id = R.string.ingredients),
    )

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val pagerState = rememberPagerState {
        tabs.size
    }

    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage

        }
    }

    val recipeDetailState by recipeDetailViewModel.recipeDetailState.collectAsState()

    Box(modifier = modifier.fillMaxWidth()) {

        Column {
            HeaderTitle(onPopPage)
            RecipeDetailTabTabRow(selectedIndex = selectedTabIndex, tabs = tabs, onTabSelected = {
                selectedTabIndex = it
            })

            if (recipeDetailState is Resource.Loading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            if (recipeDetailState is Resource.Content) {
                val state = recipeDetailState as Resource.Content
                HorizontalPager(
                    state = pagerState, modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalAlignment = Alignment.Top
                ) { index ->
                    when (index) {
                        0 -> OverviewContent(recipeEntity = state.data)
                        1 -> IngredientsContent(ingredientList = state.data.ingredients)
                    }
                }
            }

        }
    }
}


@ExperimentalMaterial3Api
@Composable
fun HeaderTitle(
    onPopPage: () -> Unit

) {

    TopAppBar(title = {
        Text(
            text = "Details",
            textAlign = TextAlign.Start,

            )
    }, navigationIcon = {
        IconButton(
            onClick = onPopPage,
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
        }
    })

}