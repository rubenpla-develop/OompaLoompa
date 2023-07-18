package com.rubenpla.oompaloompa.compose

import android.content.Context
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.rubenpla.oompaloompa.R
import com.rubenpla.oompaloompa.ui.common.HomeAppBar
import com.rubenpla.oompaloompa.ui.theme.OompaLoompaTheme
import org.junit.Rule
import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`

class HomeAppBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()
        //createAndroidComposeRule<MainActivity>()

    @Test
    fun given_title_is_shown_in_home_app_bar() {
        composeTestRule.setContent {
            OompaLoompaTheme() {
                HomeAppBar(
                    title = "Test Title",
                    openProfessionFilter = { },
                    openGenderFilter = { },
                    modifier = Modifier
                )
            }
        }
    }

    @Test
    fun given_filter_icons_are_shown_in_home_app_bar() {
        composeTestRule.setContent {
            OompaLoompaTheme() {
                HomeAppBar(
                    title = "Test Title",
                    openProfessionFilter = { },
                    openGenderFilter = { },
                    modifier = Modifier
                )
            }
        }

        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

        composeTestRule.onNodeWithContentDescription(context.resources
            .getString(R.string.content_description_top_app_bar_profession_filter_button)).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(context.resources
            .getString(R.string.content_description_top_app_bar_gender_filter_button)).assertIsDisplayed()
    }

    @Test
    fun given_filter_buttons_are_clickable_in_home_app_bar() {
        var professionFilterTestClick = false
        var genderFilterTestClick = false

        composeTestRule.setContent {
            OompaLoompaTheme() {
                HomeAppBar(
                    title = "Test Title",
                    openProfessionFilter = { professionFilterTestClick = true },
                    openGenderFilter = { genderFilterTestClick = true },
                    modifier = Modifier
                )
            }
        }

        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

        composeTestRule.onNodeWithContentDescription(context.resources
            .getString(R.string.content_description_top_app_bar_profession_filter_button)).performClick()
        composeTestRule.onNodeWithContentDescription(context.resources
            .getString(R.string.content_description_top_app_bar_gender_filter_button)).performClick()

        assertThat(professionFilterTestClick, `is`(true))
        assertThat(genderFilterTestClick, `is`(true))

    }
}