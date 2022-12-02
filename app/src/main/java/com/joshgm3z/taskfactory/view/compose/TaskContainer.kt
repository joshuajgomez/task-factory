package com.joshgm3z.taskfactory.view.compose

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.joshgm3z.taskfactory.model.room.entity.Task

@Composable
fun TaskItem(index: Int) {
    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(bottom = 3.dp, start = 3.dp, end = 3.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.tertiary)
                .fillMaxWidth()
                .padding(bottom = 3.dp, start = 2.dp, end = 2.dp)
        ) {
            val (textName, textTime, pbLoading, textDuration, iconClock, textTag, space) = createRefs()

            Text(text = "Do something #$index",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier
                    .constrainAs(textName) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start, margin = 5.dp)
                    }
            )
            Text(
                text = "Nov 15, 03:34 AM",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .constrainAs(textTime) {
                        top.linkTo(textName.bottom, margin = 3.dp)
                        start.linkTo(textName.start)
                    }
            )
            Text(
                text = "Cooking",
                fontSize = 10.sp,
                color = Color.Black,
                modifier = Modifier
                    .constrainAs(textTag) {
                        top.linkTo(textTime.top)
                        bottom.linkTo(textTime.bottom)
                        start.linkTo(textTime.end, margin = 10.dp)
                    }
                    .background(Red1)
            )
//            CircularProgressIndicator(
//                modifier = Modifier
//                    .width(20.dp)
//                    .height(20.dp)
//                    .constrainAs(pbLoading) {
//                        top.linkTo(textName.top)
//                        bottom.linkTo(textName.bottom)
//                        end.linkTo(parent.end)
//                    }
//            )
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "finished",
                modifier = Modifier
                    .size(15.dp)
                    .constrainAs(pbLoading) {
                        top.linkTo(textName.top)
                        bottom.linkTo(textName.bottom)
                        end.linkTo(parent.end)
                    })
            Text(
                text = "5",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .constrainAs(textDuration) {
                        end.linkTo(parent.end)
                        bottom.linkTo(textTime.bottom)
                        top.linkTo(textTime.top)
                    }
            )
            Icon(
                imageVector = Icons.Outlined.Schedule,
                contentDescription = "duration",
                modifier = Modifier
                    .size(10.dp)
                    .constrainAs(iconClock) {
                        end.linkTo(textDuration.start, margin = 3.dp)
                        bottom.linkTo(textDuration.bottom)
                        top.linkTo(textDuration.top)
                    })
        }
    }
}

@Composable
fun Tag(tagText: String) {
    Card {
        Text(
            text = tagText,
            fontSize = 10.sp,
            color = Color.Black,
        )
    }
}

@Composable
fun TaskContainer(
    taskList: List<Task>,
    onTasksClearClick: (Task) -> Unit,
    onAddTaskClick: (Task) -> Unit
) {
    val taskCount = 10
    Card(
        shape = RoundedCornerShape(Dimens.containerCardBorderRadius),
        modifier = Modifier
            .fillMaxHeight(1f)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxSize(1f)
        ) {
            val (textTitle, list, count, addButton, deleteIcon) = createRefs()
            Text(
                text = "Tasks($taskCount)",
                modifier = Modifier
                    .constrainAs(textTitle) {
                        start.linkTo(parent.start, margin = Dimens.titleMarginStart)
                        top.linkTo(parent.top, margin = Dimens.titleMarginTop)
                    },
                fontSize = Dimens.titleFontSize,
                color = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "+Add task",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = Dimens.buttonFontSize,
                modifier = Modifier
                    .constrainAs(addButton) {
                        end.linkTo(deleteIcon.start, margin = 2.dp)
                        top.linkTo(textTitle.top)
                        bottom.linkTo(textTitle.bottom)
                    }
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = CircleShape
                    )
                    .padding(start = 6.dp, end = 6.dp, top = 1.dp, bottom = 2.dp)
            )
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Localized description",
                modifier = Modifier
                    .size(Dimens.deleteIconSize)
                    .constrainAs(deleteIcon) {
                        end.linkTo(parent.end, margin = Dimens.deleteIconMarginEnd)
                        top.linkTo(textTitle.top)
                        bottom.linkTo(textTitle.bottom)
                    })
            LazyColumn(modifier = Modifier
                .constrainAs(list) {
                    top.linkTo(textTitle.bottom, margin = Dimens.listMarginTop)
                    start.linkTo(parent.start)
                }
            ) {
                items(count = taskCount) {
                    TaskItem(it)
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun PreviewTaskContainer() {
    Material3AppTheme {
        TaskContainer(listOf(), {}, {})
    }
}

