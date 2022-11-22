package com.joshgm3z.taskfactory.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.joshgm3z.taskfactory.common.utils.Logger
import com.joshgm3z.taskfactory.model.room.TaskFactoryDb
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker
import javax.inject.Inject

class TaskRepository @Inject constructor() {

    @Inject
    lateinit var db: TaskFactoryDb
    
    private fun getCurrentTime() = System.currentTimeMillis()

    suspend fun addTask(description: String, duration: Int) {
        Logger.log(Log.DEBUG, "description = [${description}], duration = [${duration}]")
        db.taskDao().insert(
            Task(description, getCurrentTime(), duration)
        )
    }

    suspend fun addWorker(name: String) {
        Logger.log(Log.DEBUG, "name = [${name}]")
        db.workerDao().insert(
            Worker(name)
        )
    }

    suspend fun addActivityLog(description: String) {
        Logger.log(Log.DEBUG, "description = [${description}]")
        db.activityLogDao().insert(
            ActivityLog(description, getCurrentTime())
        )
    }

    fun getAllTasks(): LiveData<List<Task>> {
        Logger.log(Log.DEBUG, "")
        return db.taskDao().getAll()
    }

    fun getAllWorkers(): LiveData<List<Worker>> {
        Logger.log(Log.DEBUG, "")
        return db.workerDao().getAll()
    }

    fun getActivityLog(): LiveData<List<ActivityLog>> {
        Logger.log(Log.DEBUG, "")
        return db.activityLogDao().getAll()
    }

    suspend fun clearWorkerList() {
        Logger.log(Log.DEBUG, "")
        db.workerDao().clear()
    }

    suspend fun clearTaskList() {
        Logger.log(Log.DEBUG, "")
        db.taskDao().clear()
    }

    suspend fun clearActivityLog() {
        Logger.log(Log.DEBUG, "")
        db.activityLogDao().clear()
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
            "taskId = [${taskId}], taskStatus = [${taskStatus}], workerId = [${workerId}],"
                    + " workerName = [${workerName}], workerStatus = [${workerStatus}]"
        )
        db.taskDao()
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
            "taskId = [${taskId}], taskStatus = [${taskStatus}], workerId = [${workerId}],"
                    + " workerName = [${workerName}], workerStatus = [${workerStatus}]"
        )
        db.taskDao()
            .runTaskFinishTransaction(taskId, taskStatus, workerId, workerName, workerStatus)
    }
}