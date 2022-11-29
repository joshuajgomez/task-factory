package com.joshgm3z.taskfactory.view.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.joshgm3z.taskfactory.R

@Composable
fun ActivityContainer() {
    Card(
        shape = RoundedCornerShape(Dimens.containerCardBorderRadius)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(Color.DarkGray)
                .fillMaxSize(1f)
        ) {
            val (textTitle, deleteIcon, list) = createRefs()
            Text(
                text = "Activity Log",
                modifier = Modifier
                    .constrainAs(textTitle) {
                        top.linkTo(parent.top, margin = Dimens.titleMarginTop)
                        start.linkTo(parent.start, margin = Dimens.titleMarginStart)
                    },
                fontSize = Dimens.titleFontSize,
                color = Color.White
            )
            Image(
                painter = painterResource(id = R.drawable.ic_delete_dark),
                contentDescription = "delete icon",
                modifier = Modifier
                    .width(Dimens.deleteIconWidth)
                    .height(Dimens.deleteIconHeight)
                    .constrainAs(deleteIcon) {
                        top.linkTo(textTitle.top)
                        bottom.linkTo(textTitle.bottom)
                        end.linkTo(parent.end, margin = Dimens.deleteIconMarginEnd)
                    })
            LazyColumn(
                modifier = Modifier
                    .constrainAs(list) {
                        top.linkTo(textTitle.bottom, margin = Dimens.listMarginTop)
                        start.linkTo(parent.start)
                    }
            ) {
                items(count = 10) {
                    ActivityItem(it)
                }
            }
        }
    }
}

@Composable
fun ActivityItem(index: Int) {
    Column(
        modifier = Modifier
            .background(Color.DarkGray)
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = "Nov 5, 2:00 PM",
            fontSize = 9.sp,
            color = Color.Gray
        )
        Text(
            text = "Joshua started working on task #$index",
            fontSize = 12.sp,
            color = Color.White
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun PreviewActivity() {
    ActivityContainer()
}