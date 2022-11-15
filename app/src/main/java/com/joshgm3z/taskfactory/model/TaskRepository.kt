package com.joshgm3z.taskfactory.model

import android.content.Context
import androidx.lifecycle.LiveData
import com.joshgm3z.taskfactory.model.room.TaskFactoryDb
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker

class TaskRepository(private val context: Context) {

    private fun getDb() = TaskFactoryDb.getInstance(context)
    private fun getCurrentTime() = System.currentTimeMillis()

    fun addTask(description: String, duration: Int) =
        getDb().taskDao().insert(
            Task(description, getCurrentTime(), duration)
        )

    fun addWorker(name: String) =
        getDb().workerDao().insert(
            Worker(name)
        )

    fun addActivityLog(description: String) =
        getDb().activityLogDao().insert(
            ActivityLog(description, getCurrentTime())
        )

    fun getAllTasks(): LiveData<List<Task>> = getDb().taskDao().getAll()

    fun getAllWorkers(): LiveData<List<Worker>> = getDb().workerDao().getAll()

    fun getActivityLog(): LiveData<List<ActivityLog>> = getDb().activityLogDao().getAll()

    fun updateTaskStatus(taskId: Int, status: Int, workerName: String) {
        getDb().taskDao().updateStatus(taskId, status, workerName)
    }

    fun updateWorkerStatus(workerId: Int, status: Int) {
        getDb().workerDao().updateStatus(workerId, status)
    }

    fun incrementWorkerJobCount(workerId: Int) {
        getDb().workerDao().incrementJobCount(workerId)
    }

    fun clearWorkerList() {
        getDb().workerDao().clear()
    }

    fun clearTaskList() {
        getDb().taskDao().clear()
    }

    fun clearActivityLog() {
        getDb().activityLogDao().clear()
    }
}