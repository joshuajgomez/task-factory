package com.joshgm3z.taskfactory.model

import android.util.Log
import androidx.lifecycle.LiveData
import com.joshgm3z.taskfactory.common.utils.Logger
import com.joshgm3z.taskfactory.model.engine.ActiveTask
import com.joshgm3z.taskfactory.model.room.TaskFactoryDb
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker

class TaskRepository(
    private val db: TaskFactoryDb,
) {

    private fun getCurrentTime() = System.currentTimeMillis()

    suspend fun addTask(task: Task) {
        Logger.log(Log.DEBUG, "task = [${task}]")
        db.taskDao().insert(task)
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

    suspend fun runTaskStartTransaction(activeTask: ActiveTask) {
        val task = activeTask.task
        val worker = activeTask.worker
        Logger.log(
            Log.DEBUG,
            "task.is = [${task.id}], task.status = [${task.getStatusText()}], worker.id = [${worker.id}],"
                    + " worker.name = [${worker.name}], worker.status = [${worker.getStatusText()}]"
        )
        db.taskDao()
            .runTaskStartTransaction(task, worker)
        addActivityLog("${activeTask.worker.name} started working on ${activeTask.task.description}")
    }

    suspend fun runTaskFinishTransaction(activeTask: ActiveTask) {
        val task = activeTask.task
        val worker = activeTask.worker
        Logger.log(
            Log.DEBUG,
            "task.id = [${task.id}], task.status = [${task.getStatusText()}], worker.id = [${worker.id}],"
                    + " worker.name = [${worker.name}], worker.status = [${worker.getStatusText()}]"
        )
        db.taskDao()
            .runTaskFinishTransaction(task, worker)
        addActivityLog("${activeTask.worker.name} finished ${activeTask.task.description}")
    }
}