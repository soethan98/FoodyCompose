package com.soethan.foodycompose.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@ExperimentalMaterial3Api
@Composable
fun MainHeaderTitle(
    title: String,
    actionMenu: @Composable () -> Unit,
) {

    TopAppBar(title = {
        Text(
            text = title,
            textAlign = TextAlign.Start,
        )
    }, actions = {
        actionMenu()

    }, colors = TopAppBarDefaults.mediumTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.onPrimary
    )
    )

}

@Composable
fun SearchMenu(onTrigger: () -> Unit) {
    IconButton(onClick = onTrigger) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "Favorite",
            tint = Color.White
        )
    }

}


@Composable
fun ThemeSwitcherMenu() {
    Icon(
        imageVector = Icons.Filled.AccountCircle,
        contentDescription = "Theme",
        tint = Color.White
    )
}