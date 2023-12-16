package com.soethan.foodycompose.presentation.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.soethan.foodycompose.R
import com.soethan.foodycompose.presentation.navigation.NavigationModel

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




