package com.soethan.foodycompose.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign

@ExperimentalMaterial3Api
@Composable
fun HeaderTitle(
    onPopPage: () -> Unit

) {

    TopAppBar(title = {
        Text(
            text = "Details",
            textAlign = TextAlign.Start,

            )
    }, navigationIcon = {
        IconButton(
            onClick = onPopPage,
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
        }
    })

}