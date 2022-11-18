package com.joshgm3z.taskfactory.model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import com.joshgm3z.taskfactory.common.utils.Logger
import com.joshgm3z.taskfactory.model.room.TaskFactoryDb
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker

class TaskRepository(private val context: Context) {

    private fun getDb() = TaskFactoryDb.getInstance(context)
    private fun getCurrentTime() = System.currentTimeMillis()

    suspend fun addTask(description: String, duration: Int) {
        Logger.log(Log.DEBUG, "description = [${description}], duration = [${duration}]")
        getDb().taskDao().insert(
            Task(description, getCurrentTime(), duration)
        )
    }

    suspend fun addWorker(name: String) {
        Logger.log(Log.DEBUG, "name = [${name}]")
        getDb().workerDao().insert(
            Worker(name)
        )
    }

    suspend fun addActivityLog(description: String) {
        Logger.log(Log.DEBUG, "description = [${description}]")
        getDb().activityLogDao().insert(
            ActivityLog(description, getCurrentTime())
        )
    }

    fun getAllTasks(): LiveData<List<Task>> {
        Logger.log(Log.DEBUG, "")
        return getDb().taskDao().getAll()
    }

    fun getAllWorkers(): LiveData<List<Worker>> {
        Logger.log(Log.DEBUG, "")
        return getDb().workerDao().getAll()
    }

    fun getActivityLog(): LiveData<List<ActivityLog>> {
        Logger.log(Log.DEBUG, "")
        return getDb().activityLogDao().getAll()
    }

    suspend fun clearWorkerList() {
        Logger.log(Log.DEBUG, "")
        getDb().workerDao().clear()
    }

    suspend fun clearTaskList() {
        Logger.log(Log.DEBUG, "")
        getDb().taskDao().clear()
    }

    suspend fun clearActivityLog() {
        Logger.log(Log.DEBUG, "")
        getDb().activityLogDao().clear()
    }

    suspend fun runTaskStartTransaction(
        taskId: Int,
        taskStatus: Int,
        workerId: Int,
        workerName: String,
        workerStatus: Int
    ) {
        Logger.log(
            Log.DEBUG,
            "taskId = [${taskId}], taskStatus = [${taskStatus}], workerId = [${workerId}], workerName = [${workerName}], workerStatus = [${workerStatus}]"
        )
        getDb().taskDao()
            .runTaskStartTransaction(taskId, taskStatus, workerId, workerName, workerStatus)
    }

    suspend fun runTaskFinishTransaction(
        taskId: Int,
        taskStatus: Int,
        workerId: Int,
        workerName: String,
        workerStatus: Int
    ) {
        Logger.log(
            Log.DEBUG,
            "taskId = [${taskId}], taskStatus = [${taskStatus}], workerId = [${workerId}], workerName = [${workerName}], workerStatus = [${workerStatus}]"
        )
        getDb().taskDao()
            .runTaskFinishTransaction(taskId, taskStatus, workerId, workerName, workerStatus)
    }
}