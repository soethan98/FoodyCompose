package com.soethan.foodycompose.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.soethan.foodycompose.domain.models.DIET_TYPE_LIST
import com.soethan.foodycompose.domain.models.DietType
import com.soethan.foodycompose.domain.models.MEAL_TYPE_LIST
import com.soethan.foodycompose.domain.models.MealAndDietType
import com.soethan.foodycompose.domain.models.MealType


@Composable
fun FilterSheetContent(
    modifier: Modifier = Modifier, selectedItem: MealAndDietType,
    onApplyFilter: (MealAndDietType) -> Unit
) {

    var selectedMeal by remember { mutableStateOf(selectedItem.selectedMealType.value) }
    var selectedDiet by remember { mutableStateOf(selectedItem.selectedDietType.value) }

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp
            ),
    ) {
        FilterChips(
            title = "Meal",
            chips = MEAL_TYPE_LIST.map { it.value },
            onSelectedChanged = {
                selectedMeal = it
            },
            selectedItem = selectedMeal
        )
        Spacer(modifier = Modifier.height(24.dp))
        FilterChips(
            title = "Diet",
            chips = DIET_TYPE_LIST.map { it.value },
            onSelectedChanged = {
                selectedDiet = it
            },
            selectedItem = selectedDiet
        )
        Spacer(modifier = Modifier.height(24.dp))
        ElevatedButton(onClick = {
            onApplyFilter(
                MealAndDietType(MEAL_TYPE_LIST.single { it.value.lowercase() == selectedMeal.lowercase() },
                    DIET_TYPE_LIST.single { it.value.lowercase() == selectedDiet.lowercase() })
            )
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Apply".uppercase())
        }
    }
}

@Composable
fun FilterChips(
    modifier: Modifier = Modifier,
    title: String,
    chips: List<String>,
    onSelectedChanged: (String) -> Unit = {},
    selectedItem: String
) {
    Column(modifier = modifier) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
        LazyRow(userScrollEnabled = true, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(chips.size) {
                FilterChip(selected = selectedItem.lowercase() == chips[it].lowercase(), onClick = {

                    onSelectedChanged(chips[it])
                }, label = {
                    Text(text = chips[it])
                })
            }
        }
    }


}