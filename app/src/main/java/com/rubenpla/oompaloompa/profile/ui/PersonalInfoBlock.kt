package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cabin
import androidx.compose.material.icons.filled.Cookie
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.entity.EmployeeDetailEntity
import com.rubenpla.oompaloompa.ui.theme.Black
import com.rubenpla.oompaloompa.ui.theme.PinkA400

@Composable
fun PersonalInfoBlock(employeeDetail: EmployeeDetailEntity?) {
    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
        val (ageLabel, AgeValue, genderLabel, genderValue,
            heightLabel, heightValue, countryLabel, countryValue) = createRefs()

        val startGuideLine = createGuidelineFromStart(0.09f)
        val endGuideline = createGuidelineFromEnd(0.48f)
        Row(modifier = Modifier
            .wrapContentSize()
            .constrainAs(ageLabel) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(startGuideLine, margin = 16.dp)
            }) {
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                imageVector = Icons.Filled.Update,
                contentDescription = "Age Label Icon",
                tint = PinkA400
            )
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = "Age",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Black
            )
        }
        Text(
            modifier = Modifier.constrainAs(AgeValue) {
                top.linkTo(ageLabel.bottom, margin = 8.dp)
                start.linkTo(ageLabel.start)
                end.linkTo(ageLabel.end)
            },
            text = employeeDetail?.age.toString(),
            textAlign = TextAlign.Center,
            color = Black,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        )
        Row(modifier = Modifier
            .wrapContentSize()
            .constrainAs(genderLabel) {
                top.linkTo(parent.top, margin = 16.dp)
                start.linkTo(endGuideline, margin = 16.dp)
            }) {
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                imageVector = Icons.Filled.StarRate,
                contentDescription = "Gender label Icon",
                tint = PinkA400
            )
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = "Gender",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Black
            )
        }
        Text(
            modifier = Modifier.constrainAs(genderValue) {
                top.linkTo(genderLabel.bottom, margin = 8.dp)
                start.linkTo(genderLabel.start)
                end.linkTo(genderLabel.end)
            },
            text = employeeDetail?.gender ?: "",
            textAlign = TextAlign.Center,
            color = Black,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )

        Row(modifier = Modifier
            .wrapContentSize()
            .constrainAs(heightLabel) {
                top.linkTo(AgeValue.bottom, margin = 16.dp)
                start.linkTo(startGuideLine, margin = 16.dp)
            }) {
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                imageVector = Icons.Filled.Cabin,
                contentDescription = "Height Label Icon",
                tint = PinkA400
            )
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = "Height",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Black
            )

        }
        Text(
            modifier = Modifier.constrainAs(heightValue) {
                top.linkTo(heightLabel.bottom, margin = 8.dp)
                start.linkTo(heightLabel.start)
                end.linkTo(heightLabel.end)
            },
            text = employeeDetail?.age.toString(),
            textAlign = TextAlign.Center,
            color = Black,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        )
        Row(modifier = Modifier
            .wrapContentSize()
            .constrainAs(countryLabel) {
                top.linkTo(genderValue.bottom, margin = 16.dp)
                start.linkTo(endGuideline, margin = 16.dp)
            }) {
            Icon(
                modifier = Modifier.padding(start = 8.dp),
                imageVector = Icons.Filled.Cookie,
                contentDescription = "Country label Icon",
                tint = PinkA400
            )
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = employeeDetail?.country ?: "",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Black
            )
        }
        Text(
            modifier = Modifier.constrainAs(countryValue) {
                top.linkTo(countryLabel.bottom, margin = 8.dp)
                start.linkTo(countryLabel.start)
                end.linkTo(genderLabel.end)
            },
            text = "Spain",
            textAlign = TextAlign.Center,
            color = Black,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp
        )
    }
}