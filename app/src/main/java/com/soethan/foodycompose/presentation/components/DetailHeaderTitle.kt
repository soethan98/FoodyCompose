package com.soethan.foodycompose.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.soethan.foodycompose.R

@ExperimentalMaterial3Api
@Composable
fun DetailHeaderTitle(
    onPopPage: () -> Unit,
    onAddToFavorite: () -> Unit
) {

    TopAppBar(title = {
        Text(
            text = stringResource(id = R.string.details),
            textAlign = TextAlign.Start,

            )
    }, navigationIcon = {
        IconButton(
            onClick = onPopPage,
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
        }
    }, actions = {
        IconButton(onClick = { onAddToFavorite() }) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
                tint = Color.White
            )
        }
    })

}