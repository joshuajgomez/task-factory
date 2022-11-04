package com.joshgm3z.taskfactory.model

import android.content.Context
import com.joshgm3z.taskfactory.model.room.TaskFactoryDb
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker

class TaskRepository(private val context: Context) {

    private fun getDb() = TaskFactoryDb.getInstance(context)
    private fun getCurrentTime() = System.currentTimeMillis()

    fun addTask(description: String, duration: Long) =
        getDb().taskDao().insert(
            Task(description, getCurrentTime(), duration)
        )

    fun addWorker(name: String) =
        getDb().workerDao().insert(
            Worker(name)
        )

    fun getAllTasks(): List<Task> = getDb().taskDao().getAll()

    fun getAllWorkers(): List<Worker> = getDb().workerDao().getAll()
}