@file:OptIn(ExperimentalFoundationApi::class)

package com.joshgm3z.taskfactory.view.container.task

import android.content.res.Configuration
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.joshgm3z.taskfactory.utils.RandomData
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.view.common.*
import com.joshgm3z.taskfactory.view.common.Material3AppTheme

@Composable
fun TaskContainer(
    taskList: List<Task>,
    onTasksClearClick: () -> Unit,
    onAddTaskClick: () -> Unit,
) {
    Log.w("Josh", "Re-compose: TaskContainer")
    Card(
        shape = RoundedCornerShape(containerCardBorderRadius),
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxSize()
        ) {
            val (textTitle, list, addButton, deleteIcon) = createRefs()
            Text(
                text = "Tasks (${taskList.size})",
                modifier = Modifier
                    .constrainAs(textTitle) {
                        start.linkTo(parent.start, margin = titleMarginStart)
                        top.linkTo(parent.top, margin = titleMarginTop)
                    },
                fontSize = titleFontSize,
                color = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "+Add task",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = buttonFontSize,
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
                    .clickable { onAddTaskClick() }
            )
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Localized description",
                modifier = Modifier
                    .size(deleteIconSize)
                    .constrainAs(deleteIcon) {
                        end.linkTo(parent.end, margin = deleteIconMarginEnd)
                        top.linkTo(textTitle.top)
                        bottom.linkTo(textTitle.bottom)
                    }
                    .clickable { onTasksClearClick() })
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .constrainAs(list) {
                        top.linkTo(textTitle.bottom, margin = listMarginTop)
                        start.linkTo(parent.start)
                    }

            ) {
                TaskList(taskList = taskList)
            }
        }
    }
}

@Composable
fun TaskList(taskList: List<Task>) {
    AnimatedVisibility(
        visible = taskList.isEmpty(),
        modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 30.dp)
    ) {
        Text(
            text = "No tasks added. Click on +Add to create a random task",
            textAlign = TextAlign.Center,
            fontSize = 13.sp,
            lineHeight = 16.sp,
            color = Gray4
        )
    }
    val groupedBy = taskList.sortedBy { it.status }.groupBy { it.status }
    LazyColumn(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        groupedBy.forEach { (taskStatus, taskListPart) ->
            stickyHeader {
                Text(
                    text = "${Task.getStatusText(taskStatus)} (${taskListPart.size})",
                    fontSize = 10.sp,
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
            items(items = taskListPart) {
                TaskItem(it)
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true, showSystemUi = true)
@Composable
fun PreviewTaskContainer() {
    val list = RandomData.getTaskList()
    list[3].status = Task.STATUS_ONGOING
    list[3].activeWorkerName = "Someone"

    list[4].status = Task.STATUS_ONGOING
    list[4].activeWorkerName = "Someone"

    list[5].status = Task.STATUS_FINISHED
    list[5].status = Task.STATUS_FINISHED
    Material3AppTheme {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(all = 20.dp)
        ) {
            Surface(modifier = Modifier.fillMaxWidth(0.55f)) {
                TaskContainer(list, {}, {})
            }
            Surface(modifier = Modifier.fillMaxWidth(0.5f)) {}
        }
    }
}

