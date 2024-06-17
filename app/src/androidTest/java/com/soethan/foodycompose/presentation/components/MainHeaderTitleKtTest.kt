package com.soethan.foodycompose.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.text.style.TextAlign
import io.mockk.mockk
import io.mockk.verify

import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*


class MainHeaderTitleKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testSearchMenuIconClickTriggersCallback() {
        var clicked = false
        composeTestRule.setContent {
            SearchMenu (
                onTrigger = {
                    clicked = true
                }
            )

        }
        val iconNode = composeTestRule.onNodeWithContentDescription("Favorite")
        iconNode.assertIsDisplayed()
        iconNode.performClick()
        assertTrue(clicked)

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Test
    fun testMainHeaderTitle(){
        composeTestRule.setContent { MainHeaderTitle(title = "App", actionMenu = {}) }

        composeTestRule.onNodeWithText("App").assertIsDisplayed()

        }
    }

