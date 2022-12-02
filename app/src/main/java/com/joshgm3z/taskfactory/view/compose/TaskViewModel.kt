package com.joshgm3z.taskfactory.view.compose

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.joshgm3z.taskfactory.common.utils.RandomData
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker

class TaskViewModel : ViewModel() {

    val taskList = mutableStateListOf<Task>()

    val workerList = mutableStateListOf<Worker>()

    val logList = mutableStateListOf<ActivityLog>()

    fun onAddTaskClick() {
        val task = RandomData.getTask()
        taskList.add(task)
        logList.add(
            ActivityLog(
                description = "New task added: ${task.description}",
                dateFinished = System.currentTimeMillis()
            )
        )
    }

    fun onAddWorkerClick() {
        val worker = Worker(RandomData.getWorkerName())
        workerList.add(worker)
        logList.add(
            ActivityLog(
                description = "New worker recruited: ${worker.name}",
                dateFinished = System.currentTimeMillis()
            )
        )
    }

    fun onClearTasksClick() {
        taskList.clear()
    }

    fun onClearWorkersClick() {
        workerList.clear()
    }

    fun onClearLogClick() {
        logList.clear()
    }

}