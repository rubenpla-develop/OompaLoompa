package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.entity.EmployeeDetailEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.entity.EmployeeFavoritesEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.ui.viewmodel.EmployeeDetailIntent
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.ui.viewmodel.EmployeeDetailState
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.ui.viewmodel.EmployeeDetailViewModel
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.naigation.DEFAULT_EMPLOYEE_ID
import com.rubenpla.oompaloompa.ui.theme.PinkA400
import com.rubenpla.oompaloompa.ui.theme.White

@Composable
fun EmployeeDetailScreen(
    employeeId: Int? = DEFAULT_EMPLOYEE_ID,
    viewModel: EmployeeDetailViewModel = hiltViewModel(),
    onBackButtonPressed: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.setEmployeeId(employeeId!!)
        viewModel.dispatchIntent(EmployeeDetailIntent.EmployeeDetail)
    }

    val uiState = viewModel.state.collectAsState()

    when (uiState.value) {
        is EmployeeDetailState.InitialState -> {
            Log.i("EmployeeDetail", "InitialState")
        }

        is EmployeeDetailState.LoadingState -> {
            Log.i("EmployeeDetail", "LoadingState")
        }

        is EmployeeDetailState.EmployeeDetailData -> {
            Log.i("EmployeeDetail", "Get employee detail State")
            val employeeDetail =
                (uiState.value as EmployeeDetailState.EmployeeDetailData).employeeDetail

            EmployeeDetails(employeeDetail, onBackButtonPressed)

        }
        else -> {}
    }
}

@Composable
fun EmployeeDetails(
    employeeDetail: EmployeeDetailEntity?,
    onBackButtonPressed: () -> Unit
) {
    val scrollState = rememberScrollState()

    ConstraintLayout {
        val (employeeDetailsView, backButton) = createRefs()

        Column(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxSize()
                .verticalScroll(scrollState)
                .testTag("EmployeeDetailsParent")
                .constrainAs(employeeDetailsView) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            MainHeader(employeeDetail)
            PersonalInfoBlock(employeeDetail)
            DescriptionBlock(employeeDetail)
            QuotaBlock(employeeDetail)
        }

        IconButton(onClick = { onBackButtonPressed() },
            modifier = Modifier
                .background(PinkA400, shape = CircleShape)
                .border(1.dp, White, shape = CircleShape)
                .size(48.dp)
                .constrainAs(backButton) {
                    start.linkTo(parent.start, margin = 16.dp)
                    top.linkTo(parent.top, margin = 16.dp)
                }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back Button",
                tint = White
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun EmployeeDetailPreview() {
    EmployeeDetails(EmployeeDetailEntity(
        "Ruben",
        "Pla Ferrero",
        "",
        "",
        "",
        "",
        0,
        "",
        0,
        EmployeeFavoritesEntity("", "", "", ""),
        "",
        ""
    )) {}
}

const val MEDIUM_LOREM_IPSUM =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Volutpat odio facilisis mauris sit. Amet venenatis urna cursus eget nunc scelerisque viverra mauris. Massa tempor nec feugiat nisl. Convallis a cras semper auctor neque. Lectus sit amet est placerat in. Adipiscing tristique risus nec feugiat."
const val HUGE_LOREM_IPSUM =
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Amet cursus sit amet dictum sit amet justo donec enim. Consectetur libero id faucibus nisl tincidunt eget nullam non. Lorem donec massa sapien faucibus et molestie ac feugiat sed. Tellus in metus vulputate eu. Lacus sed viverra tellus in hac habitasse platea dictumst. Duis ut diam quam nulla. Rhoncus urna neque viverra justo nec. Eget nunc lobortis mattis aliquam faucibus purus in. Nunc id cursus metus aliquam eleifend mi in nulla. Blandit turpis cursus in hac habitasse platea dictumst. Enim eu turpis egestas pretium aenean pharetra. Velit egestas dui id ornare arcu odio. Vitae congue mauris rhoncus aenean vel elit scelerisque mauris pellentesque. Velit laoreet id donec ultrices tincidunt arcu. Tellus integer feugiat scelerisque varius morbi enim nunc. Sit amet justo donec enim. Lectus quam id leo in vitae turpis massa sed elementum. Molestie a iaculis at erat pellentesque adipiscing commodo. Felis eget velit aliquet sagittis id."