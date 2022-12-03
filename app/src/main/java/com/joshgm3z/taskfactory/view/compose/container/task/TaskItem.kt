package com.joshgm3z.taskfactory.view.compose.container.task

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.filled.ManageAccounts
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.joshgm3z.taskfactory.common.utils.DateUtil
import com.joshgm3z.taskfactory.common.utils.RandomData
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.view.compose.common.Material3AppTheme

@Composable
fun TaskItem(task: Task) {
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
            val (textName, textTime, pbLoading, textDuration, iconClock, textTag) = createRefs()

            Column(
                modifier = Modifier
                    .constrainAs(textName) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start, margin = 5.dp)
                    }) {
                Text(
                    text = task.description,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onTertiary,
                )
                StatusText(task)
            }
            Text(
                text = DateUtil.getPrettyDate(task.timeAdded),
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier
                    .constrainAs(textTime) {
                        top.linkTo(textName.bottom, margin = 4.dp)
                        start.linkTo(textName.start)
                    }
            )
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .constrainAs(textTag) {
                        bottom.linkTo(textTime.bottom)
                        start.linkTo(textTime.end, margin = 4.dp)
                    }) {
                TypeTag(type = task.type)
            }
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .constrainAs(pbLoading) {
                        top.linkTo(textName.top, margin = 2.dp)
                        end.linkTo(parent.end)
                    }) {
                StatusIcon(task.status)
            }
            Text(
                text = "${task.duration}",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onTertiary,
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
fun StatusText(task: Task) {
    AnimatedVisibility(visible = task.status == Task.STATUS_ONGOING) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Filled.ManageAccounts,
                contentDescription = "active worker",
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = "In progress by ${task.activeWorkerName}",
                fontSize = 13.sp
            )
        }
    }
}

@Composable
fun StatusIcon(status: Int) {
    AnimatedVisibility(visible = status == Task.STATUS_ONGOING) {
        CircularProgressIndicator(
            modifier = Modifier.size(20.dp)
        )
    }
    AnimatedVisibility(visible = status != Task.STATUS_ONGOING) {
        Icon(
            imageVector = when (status) {
                Task.STATUS_FINISHED -> Icons.Filled.CheckCircle
                else -> Icons.Filled.HourglassEmpty
            },
            contentDescription = "finished",
            modifier = Modifier.size(15.dp)
        )
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewTaskItem() {
    val addedTask = RandomData.getTask()
    addedTask.description = "Added task"
    addedTask.status = Task.STATUS_ADDED

    val ongoingTask = RandomData.getTask()
    ongoingTask.description = "Ongoing task"
    ongoingTask.status = Task.STATUS_ONGOING

    val finishedTask = RandomData.getTask()
    finishedTask.description = "Finished task"
    finishedTask.status = Task.STATUS_FINISHED

    Material3AppTheme() {
        Column {
            TaskItem(task = addedTask)
            TaskItem(task = ongoingTask)
            TaskItem(task = finishedTask)
        }
    }
}