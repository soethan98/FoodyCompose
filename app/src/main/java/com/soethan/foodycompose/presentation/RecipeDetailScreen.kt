package com.soethan.foodycompose.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun RecipeDetailScreen() {
    Box(modifier = Modifier.fillMaxWidth()) {
        Text(text = "DetailScreen", textAlign = TextAlign.Center)
    }
}