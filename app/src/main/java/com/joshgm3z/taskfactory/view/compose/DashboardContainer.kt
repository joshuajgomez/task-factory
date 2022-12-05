package com.joshgm3z.taskfactory.view.compose

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.joshgm3z.taskfactory.common.utils.RandomData
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker
import com.joshgm3z.taskfactory.view.compose.common.Material3AppTheme
import com.joshgm3z.taskfactory.view.compose.container.log.LogContainer
import com.joshgm3z.taskfactory.view.compose.container.task.TaskContainer
import com.joshgm3z.taskfactory.view.compose.container.worker.WorkContainer

@Composable
fun DashboardContainer(
    taskList: List<Task>,
    workerList: List<Worker>,
    logList: List<ActivityLog>,
    onClearTasksClick: () -> Unit,
    onClearWorkersClick: () -> Unit,
    onClearLogClick: () -> Unit,
    onAddWorkerClick: () -> Unit,
    onAddTaskClick: () -> Unit,
) {
    Surface {
        ConstraintLayout(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            val (textTitle, layoutTask, layoutLog, layoutWorker) = createRefs()

            // App Title
            Text(
                fontSize = 20.sp,
                text = "task-factory",
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .constrainAs(textTitle) {
                        top.linkTo(parent.top, margin = 10.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            // Task container
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .fillMaxHeight()
                    .constrainAs(layoutTask) {
                        top.linkTo(textTitle.bottom, margin = 50.dp)
                        start.linkTo(parent.start, margin = 3.dp)
                        bottom.linkTo(parent.bottom)
                    }) {
                TaskContainer(
                    taskList = taskList,
                    onTasksClearClick = { onClearTasksClick() },
                    onAddTaskClick = { onAddTaskClick() },
                )
            }

            // Workers container
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth(0.48f)
                    .fillMaxHeight(0.49f)
                    .constrainAs(layoutWorker) {
                        top.linkTo(layoutTask.top)
                        start.linkTo(layoutTask.end, margin = 3.dp)
                        end.linkTo(parent.end, margin = 3.dp)
                    }
            ) {
                WorkContainer(
                    workerList = workerList,
                    onWorkersClearClick = { onClearWorkersClick() },
                    onAddWorkerClick = { onAddWorkerClick() }
                )
            }

            // Log container
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .fillMaxWidth(0.48f)
                    .fillMaxHeight(0.45f)
                    .constrainAs(layoutLog) {
                        top.linkTo(layoutWorker.bottom, margin = 3.dp)
                        start.linkTo(layoutWorker.start)
                        end.linkTo(layoutWorker.end)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                LogContainer(
                    logList = logList,
                    onClearLogClick = { onClearLogClick() })
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true, showSystemUi = true)
@Composable
fun PreviewDashboard() {
    Material3AppTheme() {
        val taskList = RandomData.getTaskList()
        val workerList = RandomData.getWorkerList()
        val logList = RandomData.getActivityList()
        DashboardContainer(
            taskList = taskList,
            workerList = workerList,
            logList = logList,
            onAddTaskClick = {},
            onAddWorkerClick = {},
            onClearWorkersClick = {},
            onClearTasksClick = {},
            onClearLogClick = {},
        )
    }
}