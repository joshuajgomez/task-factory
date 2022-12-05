package com.joshgm3z.taskfactory.view.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joshgm3z.taskfactory.common.utils.RandomData
import com.joshgm3z.taskfactory.model.TaskRepository
import com.joshgm3z.taskfactory.model.engine.TaskEngine
import kotlinx.coroutines.launch

class TaskViewModel(
    private val repository: TaskRepository,
) : ViewModel() {

    init {
        TaskEngine(repository)
    }

    val taskList = repository.getAllTasks()

    val workerList = repository.getAllWorkers()

    val logList = repository.getActivityLog()

    fun onAddTaskClick() {
        val task = RandomData.getTask()
        viewModelScope.launch {
            repository.addTask(task)
            repository.addActivityLog(
                description = "New task added: ${task.description}",
            )
        }
    }

    fun onAddWorkerClick() {
        val worker = RandomData.getWorkerName()
        viewModelScope.launch {
            repository.addWorker(worker)
            repository.addActivityLog(
                description = "New worker recruited: $worker",
            )
        }
    }

    fun onClearTasksClick() {
        viewModelScope.launch {
            repository.clearTaskList()
        }
    }

    fun onClearWorkersClick() {
        viewModelScope.launch {
            repository.clearWorkerList()
        }
    }

    fun onClearLogClick() {
        viewModelScope.launch {
            repository.clearActivityLog()
        }
    }

}