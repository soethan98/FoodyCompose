package com.soethan.foodycompose.utils

import android.health.connect.datatypes.units.Length
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerList(modifier: Modifier = Modifier, length: Int) {
    LazyColumn(modifier = modifier) {
        items(length) {
            ShimmerListItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}