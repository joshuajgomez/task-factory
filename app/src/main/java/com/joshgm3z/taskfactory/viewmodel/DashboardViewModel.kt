package com.joshgm3z.taskfactory.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.joshgm3z.taskfactory.common.utils.RandomData
import com.joshgm3z.taskfactory.model.TaskRepository
import com.joshgm3z.taskfactory.model.room.entity.Task

class DashboardViewModel() : ViewModel() {

    private var repo: TaskRepository? = null

    var mTaskList: LiveData<List<Task>>? = null

    fun createRepository(applicationContext: Context) {
        repo = TaskRepository(applicationContext)
        mTaskList = repo!!.getAllTasks()
    }

    fun addMockTask() {
        repo?.addTask(RandomData.getTaskName(), RandomData.getTaskDuration())
    }

}