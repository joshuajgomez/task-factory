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

    suspend fun addTask(description: String, duration: Int) =
        getDb().taskDao().insert(
            Task(description, getCurrentTime(), duration)
        )

    suspend fun addWorker(name: String) =
        getDb().workerDao().insert(
            Worker(name)
        )

    suspend fun addActivityLog(description: String) =
        getDb().activityLogDao().insert(
            ActivityLog(description, getCurrentTime())
        )

    suspend fun getAllTasks(): LiveData<List<Task>> = getDb().taskDao().getAll()

    suspend fun getAllWorkers(): LiveData<List<Worker>> = getDb().workerDao().getAll()

    suspend fun getActivityLog(): LiveData<List<ActivityLog>> = getDb().activityLogDao().getAll()

    suspend fun updateTaskStatus(taskId: Int, status: Int, workerName: String) {
        getDb().taskDao().updateStatus(taskId, status, workerName)
    }

    suspend fun updateWorkerStatus(workerId: Int, status: Int) {
        getDb().workerDao().updateStatus(workerId, status)
    }

    suspend fun incrementWorkerJobCount(workerId: Int) {
        getDb().workerDao().incrementJobCount(workerId)
    }

    suspend fun clearWorkerList() {
        getDb().workerDao().clear()
    }

    suspend fun clearTaskList() {
        getDb().taskDao().clear()
    }

    suspend fun clearActivityLog() {
        getDb().activityLogDao().clear()
    }

    suspend fun fetchData() {

    }
}