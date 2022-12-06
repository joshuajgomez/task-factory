package com.joshgm3z.taskfactory.view

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.joshgm3z.taskfactory.utils.RandomData
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker
import com.joshgm3z.taskfactory.view.common.Material3AppTheme
import com.joshgm3z.taskfactory.view.container.log.LogContainer
import com.joshgm3z.taskfactory.view.container.task.TaskContainer
import com.joshgm3z.taskfactory.view.container.worker.WorkContainer

@Composable
fun DashboardContainer(
    taskListLive: LiveData<List<Task>>,
    workerListLive: LiveData<List<Worker>>,
    logListLive: LiveData<List<ActivityLog>>,
    onClearTasksClick: () -> Unit,
    onClearWorkersClick: () -> Unit,
    onClearLogClick: () -> Unit,
    onAddWorkerClick: () -> Unit,
    onAddTaskClick: () -> Unit,
) {
    Log.w("Josh", "Re-compose: DashboardContainer")
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
                val taskList =
                    if (isInPreview()) RandomData.getTaskList()
                    else taskListLive.observeAsState(listOf()).value
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
                val workerList =
                    if (isInPreview()) RandomData.getWorkerList()
                    else workerListLive.observeAsState(listOf()).value
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
                val logList =
                    if (isInPreview()) RandomData.getActivityList()
                    else logListLive.observeAsState(listOf()).value
                LogContainer(
                    logList = logList,
                    onClearLogClick = { onClearLogClick() })
            }
        }
    }
}

@Composable
fun isInPreview(): Boolean = LocalInspectionMode.current

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true, showSystemUi = true)
@Composable
fun PreviewDashboard() {
    Material3AppTheme {
        DashboardContainer(
            taskListLive = MutableLiveData(),
            workerListLive = MutableLiveData(),
            logListLive = MutableLiveData(),
            onAddTaskClick = {},
            onAddWorkerClick = {},
            onClearWorkersClick = {},
            onClearTasksClick = {},
            onClearLogClick = {},
        )
    }
}