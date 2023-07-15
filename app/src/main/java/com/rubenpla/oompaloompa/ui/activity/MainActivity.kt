package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.ui.HomeScreen
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.ui.viewModel.EmployeeListViewModel
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.ui.viewModel.HomeIntent
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.naigation.Routes
import com.rubenpla.oompaloompa.ui.theme.OompaLoompaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: EmployeeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OompaLoompaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()

                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.HomeScreen.route
                    ) {
                        composable(Routes.HomeScreen.route) {
                            HomeScreen(
                                viewModel = viewModel,
                                navigationController = navigationController
                            )
                        }
                        //TODO missing composable for EmployeeProfileScreen()
                    }
                }
            }
        }

        viewModel.dispatchIntent(HomeIntent.AllEmployees)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OompaLoompaTheme {
        // HomeScreen()
    }
}