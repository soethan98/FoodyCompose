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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.soethan.foodycompose.R

@Composable
fun RecipeCardItem(modifier: Modifier = Modifier) {
    Surface(modifier = modifier.fillMaxWidth()) {
        Row {
            Box(modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(end = 8.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.beff_meal),

                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RectangleShape
                        ),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
            Column {
                Text(text = "Beef Tataki", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas dapibus ligula et ante cursus ultrices molestie et risus. Phasellus hendrerit ultrices erat vel interdum. Integer turpis lectus, ullamcorper at viverra non, laoreet vitae augue. Vivamus luctus faucibus dolor tempus venenatis. Aliquam vehicula luctus laoreet. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Interdum et malesuada fames ac ante ipsum primis in faucibus. Nullam accumsan sem ut convallis ultricies. Nam feugiat laoreet est convallis tincidunt.",
                    style = MaterialTheme.typography.labelSmall,
                    maxLines = 3,
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
                            text = "11", style = TextStyle(
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
                            text = "45", style = TextStyle(
                                fontSize = 12.sp,
                                color = Color.Red
                            )
                        )
                    }
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


@Composable
@Preview
fun ReceipeCardItemPreview() {
    RecipeCardItem()
}