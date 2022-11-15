package com.joshgm3z.taskfactory.viewmodel

import android.content.Context
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
import kotlinx.coroutines.launch

class DashboardViewModel() : ViewModel() {

    private var repo: TaskRepository? = null

    var mTaskList: LiveData<List<Task>>? = null
    var mWorkerList: LiveData<List<Worker>>? = null
    var mActivityLogList: LiveData<List<ActivityLog>>? = null

    fun createRepository(applicationContext: Context) {
        repo = TaskRepository(applicationContext)
        viewModelScope.launch {
            mTaskList = repo!!.getAllTasks()
            mWorkerList = repo!!.getAllWorkers()
            mActivityLogList = repo!!.getActivityLog()
        }
    }

    fun onDataFetch(
        taskList: LiveData<List<Task>>?,
        workerList: LiveData<List<Worker>>?,
        activityLogList: LiveData<List<ActivityLog>>?
    ) {
        mTaskList = taskList
        mWorkerList = workerList
        mActivityLogList = activityLogList
    }

    fun addMockTask() {
        Logger.entryLog()
        val description = RandomData.getTaskName()
        viewModelScope.launch {
            repo?.addTask(description, RandomData.getTaskDuration())
        }
        logActivity("New task added: $description")
    }

    fun addMockWorker() {
        Logger.entryLog()
        val name = RandomData.getWorkerName()
        viewModelScope.launch {
            repo?.addWorker(name)
        }
        logActivity("Worker recruited: $name")
    }

    private fun logActivity(description: String) {
        Logger.log("description = [${description}]")
        viewModelScope.launch {
            repo?.addActivityLog(description)
        }
    }

    fun onTaskStart(activeTask: ActiveTask) {
        Logger.log("activeTask = [${activeTask}]")
        viewModelScope.launch {
            repo!!.updateTaskStatus(activeTask.taskId, Task.STATUS_ONGOING, activeTask.workerName)
            repo!!.updateWorkerStatus(activeTask.workerId, Worker.STATUS_BUSY)
        }
        logActivity("${activeTask.workerName} started working on ${activeTask.taskName}")
    }

    fun onTaskFinish(activeTask: ActiveTask) {
        Logger.log("activeTask = [${activeTask}]")
        viewModelScope.launch {
            repo!!.updateTaskStatus(activeTask.taskId, Task.STATUS_FINISHED, activeTask.workerName)
            repo!!.incrementWorkerJobCount(activeTask.workerId)
            repo!!.updateWorkerStatus(activeTask.workerId, Worker.STATUS_IDLE)
        }
        logActivity("${activeTask.workerName} finished ${activeTask.taskName}")
    }

    fun clearWorkerList() {
        viewModelScope.launch {
            repo!!.clearWorkerList()
        }
    }

    fun clearTaskList() {
        viewModelScope.launch {
            repo!!.clearTaskList()
        }
    }

    fun clearActivityLog() {
        viewModelScope.launch {
            repo!!.clearActivityLog()
        }
    }

}