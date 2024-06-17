package com.soethan.foodycompose.presentation.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.soethan.foodycompose.utils.AppTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test


class ThemeChooserDropDownKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testThemeChooserDropDown() {
        var selectedTheme: AppTheme = AppTheme.MODE_AUTO

//        var expanded by remember {
//            mutableStateOf(false)
//        }

        composeTestRule.setContent {
            ThemeChooserDropDown(currentTheme = selectedTheme, onThemeChange = {
                selectedTheme = it
            })
        }

        composeTestRule.onNodeWithContentDescription("menu").assertIsDisplayed()
        assertTrue(selectedTheme == AppTheme.MODE_AUTO)

        // Simulate clicking the IconButton to open the DropdownMenu
        composeTestRule.onNodeWithContentDescription("menu").performClick()

        // Verify the dropdown menu items are displayed
        composeTestRule.onNodeWithText("Auto").assertIsDisplayed()
        composeTestRule.onNodeWithText("Light").assertIsDisplayed()
        composeTestRule.onNodeWithText("Dark").assertIsDisplayed()

        // Click on "Light" to change the theme
        composeTestRule.onNodeWithText("Light").performClick()

        // Verify the callback is triggered with the correct theme
        assert(selectedTheme == AppTheme.MODE_DAY)
// Verify the dropdown menu is closed
        composeTestRule.onNodeWithText("Auto").assertDoesNotExist()
        composeTestRule.onNodeWithText("Light").assertDoesNotExist()
        composeTestRule.onNodeWithText("Dark").assertDoesNotExist()






    }




}