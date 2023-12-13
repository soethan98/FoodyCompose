package com.soethan.foodycompose.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.soethan.foodycompose.R
import com.soethan.foodycompose.domain.models.RecipeEntity
import com.soethan.foodycompose.utils.parseHTMLSpanned
import com.soethan.foodycompose.utils.toAnnotatedString
import kotlin.math.roundToInt

@Composable
fun RecipeCardItem(modifier: Modifier = Modifier, recipeEntity: RecipeEntity? = null) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        tonalElevation = 12.dp
    ) {
        Row {


//            Box(
//                modifier = Modifier
//                    .fillMaxWidth(0.4f)
//                    .padding(end = 8.dp)
//            ) {
//                AsyncImage(
//                    model = recipeEntity!!.image,
//                    contentDescription = recipeEntity.title,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.clip(RoundedCornerShape(12.dp))
//                )
//            }
            Column {
                Text(text = recipeEntity!!.title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = recipeEntity.summary!!.parseHTMLSpanned().toAnnotatedString(),
                    maxLines = 3,
                    style = MaterialTheme.typography.labelSmall,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(5.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_heart),
                            contentDescription = "favorite",
                            tint = Color.Red
                        )
                        Text(
                            text = recipeEntity.healthScore.roundToInt().toString(),
                            style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.Red
                            )
                        )
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_clock),
                            contentDescription = "favorite",
                            tint = Color.Magenta
                        )
                        Text(
                            text = "${recipeEntity.readyInMinutes}", style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.Red
                            )
                        )
                    }
                    if (recipeEntity.vegan)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_leaf),
                                contentDescription = "favorite",
                                tint = Color.Gray
                            )
                            Text(
                                text = "Vegan", style = TextStyle(
                                    fontSize = 12.sp,
                                    color = Color.Red
                                )
                            )
                        }
                }
            }
        }
    }
}


