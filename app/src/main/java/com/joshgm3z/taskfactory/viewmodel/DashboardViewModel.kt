package com.joshgm3z.taskfactory.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joshgm3z.taskfactory.common.utils.Logger
import com.joshgm3z.taskfactory.common.utils.RandomData
import com.joshgm3z.taskfactory.model.TaskRepository
import com.joshgm3z.taskfactory.model.engine.ActiveTask
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var repo: TaskRepository

    var mTaskList: LiveData<List<Task>>? = null
    var mWorkerList: LiveData<List<Worker>>? = null
    var mActivityLogList: LiveData<List<ActivityLog>>? = null

    fun createRepository() {
        viewModelScope.launch {
            mTaskList = repo.getAllTasks()
            mWorkerList = repo.getAllWorkers()
            mActivityLogList = repo.getActivityLog()
        }
    }

    fun addMockTask() {
        Logger.entryLog()
        viewModelScope.launch {
            val task = RandomData.getTask()
            repo.addTask(task)
            repo.addActivityLog("New task added: ${task.description}")
        }
    }

    fun addMockWorker() {
        Logger.entryLog()
        val name = RandomData.getWorkerName()
        viewModelScope.launch {
            repo.addWorker(name)
            repo.addActivityLog("Worker recruited: $name")
        }
    }

    fun onTaskStart(activeTask: ActiveTask) {
        Logger.log("activeTask = [${activeTask}]")
        viewModelScope.launch {
            repo.runTaskStartTransaction(
                activeTask.taskId,
                Task.STATUS_ONGOING,
                activeTask.workerId,
                activeTask.workerName,
                Worker.STATUS_BUSY
            )
            repo.addActivityLog("${activeTask.workerName} started working on ${activeTask.taskName}")
        }
    }

    fun onTaskFinish(activeTask: ActiveTask) {
        Logger.log("activeTask = [${activeTask}]")
        viewModelScope.launch {
            repo.runTaskFinishTransaction(
                activeTask.taskId,
                Task.STATUS_FINISHED,
                activeTask.workerId,
                activeTask.workerName,
                Worker.STATUS_IDLE
            )
            repo.addActivityLog("${activeTask.workerName} finished ${activeTask.taskName}")
        }
    }

    fun clearWorkerList() {
        viewModelScope.launch {
            repo.clearWorkerList()
        }
    }

    fun clearTaskList() {
        viewModelScope.launch {
            repo.clearTaskList()
        }
    }

    fun clearActivityLog() {
        viewModelScope.launch {
            repo.clearActivityLog()
        }
    }

}