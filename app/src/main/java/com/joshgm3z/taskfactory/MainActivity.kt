package com.joshgm3z.taskfactory

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.joshgm3z.taskfactory.view.DashboardContainer
import com.joshgm3z.taskfactory.viewmodel.TaskViewModel
import com.joshgm3z.taskfactory.view.common.Material3AppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Material3AppTheme {
                val viewModel by viewModel<TaskViewModel>()
                DashboardContainer(
                    taskList = viewModel.taskList,
                    workerList = viewModel.workerList,
                    logList = viewModel.logList,
                    onClearLogClick = { viewModel.onClearLogClick() },
                    onClearTasksClick = { viewModel.onClearTasksClick() },
                    onClearWorkersClick = { viewModel.onClearWorkersClick() },
                    onAddWorkerClick = { viewModel.onAddWorkerClick() },
                ) { viewModel.onAddTaskClick() }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
//@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun DefaultPreview() {
    Material3AppTheme {
//        DashboardContainer()
    }
}