package com.soethan.foodycompose.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.soethan.foodycompose.R
import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.presentation.RecipeType
import com.soethan.foodycompose.utils.parseHTMLSpanned
import com.soethan.foodycompose.utils.toAnnotatedString

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecipeDetailContent(modifier: Modifier = Modifier, recipeEntity: RecipeEntity? = null) {

    if (recipeEntity == null) {
        return Box {

        }
    }

    val recipeTypes = listOf<RecipeType>(
        RecipeType(
            title = stringResource(id = R.string.vegetarian),
            isMet = recipeEntity.vegetarian
        ),
        RecipeType(
            title = stringResource(id = R.string.vegan),
            isMet = recipeEntity.vegan
        ),
        RecipeType(
            title = stringResource(id = R.string.gluten_free),
            isMet = recipeEntity.glutenFree
        ),
        RecipeType(
            title = stringResource(id = R.string.healthy),
            isMet = recipeEntity.veryHealthy
        ),
        RecipeType(
            title = stringResource(id = R.string.dairy_free),
            isMet = recipeEntity.dairyFree
        ),
        RecipeType(
            title = stringResource(id = R.string.cheap),
            isMet = recipeEntity.cheap
        )

    )
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = recipeEntity!!.title, style = TextStyle(
                fontSize = 22.sp
            ), maxLines = 2
        )
        Spacer(modifier = Modifier.height(12.dp))

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            recipeTypes.forEach{
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_checkmark),
                        tint = if (it.isMet) Color.Green else Color.Black,
                        contentDescription = "${it.title}"
                    )

                    Text(
                        text = it.title, style = TextStyle(
                            fontSize = 14.sp
                        )
                    )

                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Spacer(modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
            .height(12.dp))
        Text(text = recipeEntity.summary?.parseHTMLSpanned()!!.toAnnotatedString())


    }
}


