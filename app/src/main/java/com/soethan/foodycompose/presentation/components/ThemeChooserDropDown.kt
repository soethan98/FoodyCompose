package com.soethan.foodycompose.presentation.components

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.soethan.foodycompose.utils.AppTheme

@Composable
fun ThemeChooserDropDown(
    modifier: Modifier = Modifier,
    currentTheme: AppTheme,
    onThemeChange: (AppTheme) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = {
        expanded = !expanded
    }) {
        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "menu")
    }

    DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
        DropdownMenuItem(
            enabled = currentTheme != AppTheme.MODE_AUTO,
            text = { Text(text = "Auto") },
            onClick = {
                onThemeChange(AppTheme.MODE_AUTO)
                expanded = false
            })
        DropdownMenuItem(
            enabled = currentTheme != AppTheme.MODE_DAY,
            text = { Text(text = "Light") },
            onClick = {
                onThemeChange(AppTheme.MODE_DAY)
                expanded = false
            })
        DropdownMenuItem(
            enabled = currentTheme != AppTheme.MODE_NIGHT,
            text = { Text(text = "Dark") },
            onClick = {
                onThemeChange(AppTheme.MODE_NIGHT)
                expanded = false
            })
    }
}