package com.rubenpla.oompaloompa.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.rubenpla.oompaloompa.R
import com.rubenpla.oompaloompa.ui.theme.PinkA400
import com.rubenpla.oompaloompa.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppBar(
    title: String,
    openProfessionFilter: () -> Unit,
    openGenderFilter: () -> Unit,
    modifier: Modifier
) {
    TopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = {
                openProfessionFilter()
            }) {
                Icon(
                    imageVector = Icons.Filled.Work,
                    contentDescription = stringResource(id = R.string.content_description_top_app_bar_profession_filter_button),
                    tint = White
                )
            }

            IconButton(onClick = {
                openGenderFilter()
            }) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = stringResource(id = R.string.content_description_top_app_bar_gender_filter_button),
                    tint = White
                )
            }
        },
        modifier = modifier,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = PinkA400,
            titleContentColor = White
        )
    )
}