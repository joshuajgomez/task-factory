package com.joshgm3z.taskfactory.view.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.joshgm3z.taskfactory.R

const val mLayoutIdTitle = "title"
const val mLayoutIdAddedTime = "added_time"
const val mLayoutIdDuration = "duration"

@Composable
fun TaskItem(index: Int) {
    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.padding(bottom = 5.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .padding(all = 5.dp)
                .fillMaxWidth(1f)
        ) {
            val (textName, textTime, pbLoading, textDuration, iconClock, textTag, space) = createRefs()

            Text(text = "Do something #$index",
                fontSize = 20.sp,
                modifier = Modifier
                    .constrainAs(textName) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .layoutId(mLayoutIdTitle)
            )
            Text(
                text = "Nov 15, 03:34 AM",
                fontSize = 10.sp,
                modifier = Modifier
                    .constrainAs(textTime) {
                        top.linkTo(textName.bottom)
                        start.linkTo(textName.start)
                    }
                    .layoutId(mLayoutIdAddedTime),
            )
            Text(
                text = "Cooking",
                fontSize = 10.sp,
                modifier = Modifier
                    .constrainAs(textTag) {
                        top.linkTo(textTime.top)
                        bottom.linkTo(textTime.bottom)
                        start.linkTo(textTime.end, margin = 10.dp)
                    }
//                    .paint(painterResource(id = R.drawable.tag_background_rounded_yellow))
                    .background(Color.Yellow)
            )
            CircularProgressIndicator(
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
                    .constrainAs(pbLoading) {
                        top.linkTo(textName.top)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                text = "5",
                fontSize = 10.sp,
                modifier = Modifier
                    .constrainAs(textDuration) {
                        end.linkTo(parent.end)
                        bottom.linkTo(textTime.bottom)
                        top.linkTo(textTime.top)
                    }
                    .layoutId(mLayoutIdDuration)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_clock),
                contentDescription = "clock",
                modifier = Modifier
                    .width(10.dp)
                    .height(10.dp)
                    .constrainAs(iconClock) {
                        end.linkTo(textDuration.start, margin = 3.dp)
                        bottom.linkTo(textDuration.bottom)
                        top.linkTo(textDuration.top)
                    }
            )
            Spacer(modifier = Modifier
                .height(50.dp)
                .width(IntrinsicSize.Max)
                .constrainAs(space) {
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
            )
        }
    }
}


@Composable
fun TaskContainer() {
    val taskCount = 10
    ConstraintLayout(
        modifier = Modifier
            .background(Color.DarkGray)
    ) {
        val (textTitle, list, count, addButton, deleteIcon) = createRefs()
        Text(
            text = "Tasks($taskCount)",
            modifier = Modifier
                .constrainAs(textTitle) {
                    start.linkTo(parent.start, margin = 10.dp)
                    top.linkTo(parent.top, margin = 10.dp)
                },
            fontSize = 20.sp,
            color = Color.White
        )
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(addButton) {
                    end.linkTo(deleteIcon.start, margin = 10.dp)
                    top.linkTo(textTitle.top)
                    bottom.linkTo(textTitle.bottom)
                }
                .height(35.dp)
        ) {
            Text(text = "+ Add task")
        }
        Image(
            painter = painterResource(id = R.drawable.ic_delete_dark),
            contentDescription = "delete",
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
                .constrainAs(deleteIcon) {
                    end.linkTo(parent.end, margin = 10.dp)
                    top.linkTo(textTitle.top)
                    bottom.linkTo(textTitle.bottom)
                }
        )
        LazyColumn(modifier = Modifier
            .constrainAs(list) {
                top.linkTo(textTitle.bottom, margin = 10.dp)
                start.linkTo(parent.start)
            }
        ) {
            items(count = taskCount) {
                TaskItem(it)
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun Preview() {
    TaskContainer()
//    TaskItem(index = 1)
}

