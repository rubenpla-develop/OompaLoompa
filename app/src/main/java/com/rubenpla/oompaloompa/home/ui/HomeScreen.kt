package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Filter2
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import coil.memory.MemoryCache
import coil.request.CachePolicy
import com.rubenpla.oompaloompa.R
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.domain.filter.FilterType
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.domain.entity.EmployeeResultsEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.ui.viewModel.EmployeeListViewModel
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.ui.viewModel.HomeIntent
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.home.ui.viewModel.HomeState
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.common.LoadingItem
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.naigation.Routes
import com.rubenpla.oompaloompa.ui.common.HomeAppBar
import com.rubenpla.oompaloompa.ui.theme.PinkA700
import com.rubenpla.oompaloompa.ui.theme.PurpleGrey40


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    viewModel: EmployeeListViewModel,
    navigationController: NavHostController
) {
    var showModal by remember { mutableStateOf(false) }
    var professionOrGenderFilter by remember { mutableStateOf(FilterType.NONE) }

    Scaffold(topBar = {
        HomeAppBar(title = stringResource(id = R.string.app_name),
            modifier = Modifier,
            openProfessionFilter = {
                showModal = !showModal
                professionOrGenderFilter = FilterType.PROFESSION
            },
            openGenderFilter = {
                showModal = !showModal
                professionOrGenderFilter = FilterType.GENDER
            })
    },
        content = { paddingValues ->
            WorkersGridList(
                viewModel = viewModel,
                paddingValues = paddingValues,
                navigationController = navigationController
            )
        })

    if (showModal) {
        ModalBottomSheetLayout(
            sheetContent = {
                val list = when (professionOrGenderFilter) {
                    FilterType.PROFESSION -> viewModel.getProfessionsFilter()
                    FilterType.GENDER -> viewModel.getGenderFilters()
                    else -> { viewModel.getProfessionsFilter() }
                }

                LazyColumn {
                    items(list.size) { index ->
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clickable {
                                showModal = false

                                viewModel.dispatchIntentWithFilters(
                                    HomeIntent.GetFilteredEmployees,
                                    professionOrGenderFilter,
                                    list[index].getFilterName()
                                )
                            }) {
                            Icon(
                                modifier = Modifier.padding(end = 8.dp),
                                imageVector = Icons.Filled.Filter2,
                                contentDescription = "Filter",
                                tint = PinkA700
                            )

                            Text(text = list[index].getFilterName(), fontSize = 14.sp)
                        }
                    }
                }
            },
            sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)
        ) {
            // Content of the screen
        }
    }
}

@Composable
fun WorkersGridList(
    viewModel: EmployeeListViewModel,
    paddingValues: PaddingValues,
    navigationController: NavHostController
) {
    val uiState = viewModel.state.collectAsState()

    when (uiState.value) {
        is HomeState.InitialState -> {
            Log.i("HomeScreen", "InitialState")
        }

        is HomeState.LoadingState -> {
            Log.i("HomeScreen", "LoadingState")
        }

        is HomeState.EmployeeListData -> {
            Log.i("HomeScreen", "EmployeeListData State")
            val employeeItems =
                (uiState.value as HomeState.EmployeeListData).employees.collectAsLazyPagingItems()
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = paddingValues.calculateTopPadding())
                    .background(color = PurpleGrey40),
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                items(employeeItems.itemCount) { itemIndex ->

                    employeeItems[itemIndex]?.let { employeeEntity ->

                        EmployeeItem(employee = employeeEntity) { id ->
                            navigationController.navigate(Routes.EmployeeProfile.createRoute(id.toString()))
                        }
                    }
                }

                employeeItems.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            item { LoadingItem() }
                            item { LoadingItem() }
                        }

                        loadState.append is LoadState.Loading -> {
                            item { LoadingItem() }
                            item { LoadingItem() }
                        }

                        loadState.refresh is LoadState.Error -> {}
                        loadState.append is LoadState.Error -> {}
                    }
                }
            }
        }

        else -> {}
    }


}

@Composable
fun EmployeeItem(
    employee: EmployeeResultsEntity,
    onEmployeeClick: (Int) -> Unit
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 20.dp),
        border = BorderStroke(2.dp, Color.DarkGray),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .height(200.dp)
            .fillMaxWidth()
            .clickable {
                Log.i("EmployeeItem", "Worker with id ${employee.id} clicked")
                onEmployeeClick(employee.id.toInt())
            }) {
        ConstraintLayout {

            val (name, gender, category, photo, spacerTop, spacerStart,
                spacerBottom, spacerEnd) = createRefs()

            Image(
                painter = rememberImagePainter(data = employee.image,
                    builder = {
                        placeholder(R.drawable.ic_employee_placeholder)
                        memoryCachePolicy(CachePolicy.ENABLED)
                    }),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(photo) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .constrainAs(spacerTop) {
                    top.linkTo(parent.top)
                })

            Spacer(modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .constrainAs(spacerBottom) {
                    bottom.linkTo(parent.bottom)
                })

            Spacer(modifier = Modifier
                .fillMaxHeight()
                .width(6.dp)
                .constrainAs(spacerStart) {
                    start.linkTo(parent.start)
                })

            Spacer(modifier = Modifier
                .fillMaxHeight()
                .width(6.dp)
                .constrainAs(spacerEnd) {
                    end.linkTo(parent.end)
                })

            Text(
                text = employee.firstName.plus(" ").plus(employee.lastName),
                color = Color.White,
                fontSize = 10.sp,
                //fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .background(Color.DarkGray, RoundedCornerShape(4.dp))
                    .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                    .padding(top = 1.dp, start = 3.dp, bottom = 1.dp, end = 3.dp)
                    .constrainAs(name) {
                        top.linkTo(spacerTop.bottom)
                        start.linkTo(spacerStart.end)
                    }
            )
            Text(
                text = employee.gender,
                color = Color.White,
                fontSize = 10.sp,
                modifier = Modifier
                    .background(Color.DarkGray, RoundedCornerShape(4.dp))
                    .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                    .padding(top = 1.dp, start = 3.dp, bottom = 1.dp, end = 3.dp)
                    .constrainAs(gender) {
                        bottom.linkTo(spacerBottom.top)
                        start.linkTo(spacerStart.end)
                    }
            )

            Text(
                text = employee.profession,
                color = Color.White,
                fontSize = 10.sp,
                modifier = Modifier
                    .background(Color.DarkGray, RoundedCornerShape(4.dp))
                    .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                    .padding(top = 1.dp, start = 3.dp, bottom = 1.dp, end = 3.dp)
                    .constrainAs(category) {
                        end.linkTo(spacerEnd.start)
                        bottom.linkTo(spacerBottom.top)
                    }
            )
        }
    }
}


//TODO FAKE LIST , DELETE IT!!!!
fun getWorkers(): List<Worker> {
    return listOf(
        Worker(
            "The Witcher",
            gender = "Male",
            profession = "Developer",
            R.drawable.ic_launcher_foreground
        ),
        Worker(
            "Spiderman",
            gender = "Male",
            profession = "Developer",
            R.drawable.ic_launcher_foreground
        ),
        Worker(
            "Wolverine", gender = "Male",
            profession = "Developer",
            R.drawable.ic_launcher_foreground
        ),
        Worker(
            "Batman", gender = "Male",
            profession = "Developer",
            R.drawable.ic_launcher_foreground
        ),
        Worker(
            "Thor", gender = "Male",
            profession = "Developer",
            R.drawable.ic_launcher_foreground
        ),
        Worker(
            "Flash",
            gender = "Male",
            profession = "Developer",
            R.drawable.ic_launcher_foreground
        ),
        Worker(
            "Green Lantern",
            gender = "Male",
            profession = "Developer",
            R.drawable.ic_launcher_foreground
        ),
        Worker(
            "Wonder Woman",
            gender = "Male",
            profession = "Developer",
            R.drawable.ic_launcher_foreground
        )
    )
}

//TODO DELETE IT
data class Worker(
    val name: String,
    val gender: String,
    val profession: String,
    val image: Int
)