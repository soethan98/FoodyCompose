package com.soethan.foodycompose.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.soethan.foodycompose.R
import com.soethan.foodycompose.domain.models.RecipeEntity

@Composable
fun RecipeDetailBackDrop(modifier: Modifier = Modifier, recipeEntity: RecipeEntity? = null) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        AsyncImage(
            model = recipeEntity!!.image,
            contentDescription = recipeEntity.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        Row(
            modifier = Modifier.align(Alignment.BottomEnd),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_heart),
                    contentDescription = "heart",
                    tint = Color.White
                )
                Text(
                    text = recipeEntity.healthScore.toString(),
                    style = MaterialTheme.typography.titleSmall.copy(color = Color.White)
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_clock),
                    contentDescription = "time",
                    tint = Color.White
                )
                Text(
                    text = recipeEntity.readyInMinutes.toString(),
                    style = MaterialTheme.typography.titleSmall.copy(color = Color.White)
                )
            }
        }

    }
}