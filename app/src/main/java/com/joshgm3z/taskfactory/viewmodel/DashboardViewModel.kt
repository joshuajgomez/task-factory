package com.joshgm3z.taskfactory.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.joshgm3z.taskfactory.common.utils.RandomData
import com.joshgm3z.taskfactory.model.TaskRepository
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker

class DashboardViewModel() : ViewModel() {

    private var repo: TaskRepository? = null

    var mTaskList: LiveData<List<Task>>? = null
    var mWorkerList: LiveData<List<Worker>>? = null
    var mActivityLogList: LiveData<List<ActivityLog>>? = null

    fun createRepository(applicationContext: Context) {
        repo = TaskRepository(applicationContext)
        mTaskList = repo!!.getAllTasks()
        mWorkerList = repo!!.getAllWorkers()
        mActivityLogList = repo!!.getActivityLog()
    }

    fun addMockTask() {
        val description = RandomData.getTaskName()
        repo?.addTask(description, RandomData.getTaskDuration())
        logActivity("New task added: $description")
    }

    fun addMockWorker() {
        val name = RandomData.getWorkerName()
        repo?.addWorker(name)
        logActivity("Worker recruited: $name")
    }

    fun logActivity(description: String){
        repo?.addActivityLog(description)
    }

}