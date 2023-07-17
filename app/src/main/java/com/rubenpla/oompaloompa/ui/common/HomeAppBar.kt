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
                    contentDescription = "Profession Filter",
                    tint = White
                )
            }

            IconButton(onClick = {
                openGenderFilter()
            }) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Gender Filter",
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