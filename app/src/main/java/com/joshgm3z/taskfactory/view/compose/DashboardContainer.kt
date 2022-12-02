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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DashboardContainer(taskViewModel: TaskViewModel = viewModel()) {
    Surface {
        ConstraintLayout(
            modifier = Modifier.background(MaterialTheme.colorScheme.primary)
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
            Surface(modifier = Modifier
                .fillMaxWidth(0.5f)
                .constrainAs(layoutTask) {
                    top.linkTo(textTitle.bottom, margin = 50.dp)
                    start.linkTo(parent.start, margin = 3.dp)
                    bottom.linkTo(parent.bottom)
                }) {
                TaskContainer(
                    taskList = taskViewModel.taskList,
                    onTasksClearClick = { taskViewModel.onClearTasksClick() },
                    onAddTaskClick = { taskViewModel.onAddTaskClick() },
                )
            }

            // Workers container
            Surface(
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
                    workerList = taskViewModel.workerList,
                    onWorkersClearClick = { taskViewModel.onClearWorkersClick() },
                    onAddWorkerClick = { taskViewModel.onAddWorkerClick() }
                )
            }

            // Log container
            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.48f)
                    .fillMaxHeight(0.49f)
                    .constrainAs(layoutLog) {
                        top.linkTo(layoutWorker.bottom, margin = 3.dp)
                        start.linkTo(layoutWorker.start)
                        end.linkTo(layoutWorker.end)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                ActivityContainer(
                    logList = taskViewModel.logList,
                    onClearLogClick = { taskViewModel.onClearLogClick() })
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewDashboard() {
    Material3AppTheme() {
        DashboardContainer()
    }
}