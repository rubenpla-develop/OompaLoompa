package com.rubenpla.oompaloompa.compose

import android.content.Context
import androidx.compose.ui.test.IdlingResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.rubenpla.oompaloompa.R
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.ui.HomeScreen
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.ui.viewModel.EmployeeListViewModel
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.ui.viewModel.HomeIntent
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.ui.viewModel.HomeState
import com.rubenpla.oompaloompa.compose.utils.ComposeIdlingResource
import com.rubenpla.oompaloompa.compose.utils.EmployeesFakeData
import com.rubenpla.oompaloompa.ui.theme.OompaLoompaTheme
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val employeeListViewModel = mockk<EmployeeListViewModel>()

    private val composeIdlingResource = ComposeIdlingResource()

    @Before
    fun init() {
        composeTestRule.registerIdlingResource(composeIdlingResource)
        MockKAnnotations.init(this, true)

        every { employeeListViewModel.createInitialState() } answers {
            composeIdlingResource.isAppIdle(false)
            HomeState.InitialState
        }
        every { employeeListViewModel.dispatchIntent(HomeIntent.AllEmployees) } answers  {
            composeIdlingResource.isAppIdle(true)
            //flow {  }
        }
    }

    @After
    fun tearDown() {
        composeTestRule.unregisterIdlingResource(composeIdlingResource)
    }

    @Test
    fun app_bar_should_be_displayed_in_home_screen() {
        val fakeData = EmployeesFakeData.getFakePagingData().flowOn(Dispatchers.IO)
            .onCompletion { composeIdlingResource.isAppIdle(true) }
        every { employeeListViewModel.dispatchIntent(HomeIntent.AllEmployees) } answers {
            MutableStateFlow(fakeData)
        }

        val navController = NavHostController(ApplicationProvider.getApplicationContext())

        composeTestRule.setContent {
            OompaLoompaTheme {
                val navigationController = rememberNavController()

                HomeScreen(
                    viewModel = employeeListViewModel,
                    navigationController = navController
                )
            }
        }

        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText(context.resources.getString(R.string.app_name)).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(context.resources
            .getString(R.string.content_description_top_app_bar_profession_filter_button)).assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription(context.resources
            .getString(R.string.content_description_top_app_bar_gender_filter_button)).assertIsDisplayed()
    }
}