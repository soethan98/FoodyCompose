package com.soethan.foodycompose.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecipeDetailTabTabRow(
    modifier: Modifier = Modifier,
    onTabSelected: (Int) -> Unit,
    selectedIndex: Int,
    tabs: List<String>
) {


    TabRow(
        selectedTabIndex = selectedIndex,
        tabs = {
            tabs.forEachIndexed { index, s ->
                Tab(
                    modifier = Modifier.padding(bottom = 10.dp),
                    selected = index == selectedIndex, onClick = {
                        onTabSelected(index)
                    }) {
                    Text(text = s)
                }
            }
        }
    )
}




