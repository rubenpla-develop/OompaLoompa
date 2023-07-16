package com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberImagePainter
import com.rubenpla.oompaloompa.R
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.profile.domain.entity.EmployeeDetailEntity
import com.rubenpla.oompaloompa.com.rubenpla.oompaloompa.ui.common.BottomRoundedArcShape
import com.rubenpla.oompaloompa.ui.theme.Black
import com.rubenpla.oompaloompa.ui.theme.PinkA400

@Composable
fun MainHeader(employeeDetail: EmployeeDetailEntity?) {
    ConstraintLayout {
        val (avatarIcon, employeeImage) = createRefs()
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .graphicsLayer {
                    clip = true
                    shape = BottomRoundedArcShape()
                }
                .constrainAs(employeeImage) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.Crop,
            painter = rememberImagePainter(
                data = employeeDetail?.image,
                builder = {
                    placeholder(R.drawable.oompa_loompa_placeholder)
                    crossfade(true)
                }
            ),
            contentDescription = "Employee Image")

        AvatarIcon(
            modifier = Modifier.constrainAs(avatarIcon) {
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
    Text(
        modifier = Modifier.padding(start = 16.dp, top = 30.dp, end = 16.dp),
        text = employeeDetail?.firstName.plus(" ").plus(employeeDetail?.lastName),
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = Black
    )
    Text(
        modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp),
        text = employeeDetail?.profession ?: "",
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        color = Black
    )
    Row(
        modifier = Modifier
            .padding(start = 16.dp, top = 8.dp, end = 16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.padding(top = 2.dp),
            imageVector = Icons.Filled.Mail,
            contentDescription = "Avatar Icon",
            tint = PinkA400
        )

        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = employeeDetail?.email ?: "",
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = Black
        )
    }
}

@Composable
fun AvatarIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .offset(0.dp, 25.dp)
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            imageVector = Icons.Filled.Person2,
            contentDescription = "Avatar Icon",
            tint = PinkA400
        )
    }
}