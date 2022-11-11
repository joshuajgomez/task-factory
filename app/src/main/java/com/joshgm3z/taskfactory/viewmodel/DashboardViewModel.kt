package com.joshgm3z.taskfactory.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.joshgm3z.taskfactory.common.utils.RandomData
import com.joshgm3z.taskfactory.model.TaskRepository
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker

class DashboardViewModel() : ViewModel() {

    private var repo: TaskRepository? = null

    var mTaskList: LiveData<List<Task>>? = null
    var mWorkerList: LiveData<List<Worker>>? = null

    fun createRepository(applicationContext: Context) {
        repo = TaskRepository(applicationContext)
        mTaskList = repo!!.getAllTasks()
        mWorkerList = repo!!.getAllWorkers()
    }

    fun addMockTask() {
        repo?.addTask(RandomData.getTaskName(), RandomData.getTaskDuration())
    }

    fun addMockWorker() {
        repo?.addWorker(RandomData.getWorkerName())
    }

}