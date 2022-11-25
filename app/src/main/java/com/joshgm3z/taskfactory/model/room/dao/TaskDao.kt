package com.joshgm3z.taskfactory.model.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker

@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Query("select * from Task")
    fun getAll(): LiveData<List<Task>>

    @Query("update Task set status=:status, active_worker_name=:workerName where id=:taskId")
    suspend fun updateTaskStatus(taskId: Int, status: Int, workerName: String)

    @Query("update Worker set status=:status where id=:workerId")
    suspend fun updateWorkerStatus(workerId: Int, status: Int)

    @Query("delete from Task")
    suspend fun clear()

    @Query("update Worker set job_count=:jobCount where id=:workerId")
    suspend fun updateWorkerJobCount(workerId: Int, jobCount: Int)

    @Transaction
    suspend fun runTaskStartTransaction(task: Task, worker: Worker) {
        updateTaskStatus(task.id, task.status, worker.name)
        updateWorkerStatus(worker.id, worker.status)
    }

    @Transaction
    suspend fun runTaskFinishTransaction(task: Task, worker: Worker) {
        updateTaskStatus(task.id, task.status, worker.name)
        updateWorkerJobCount(worker.id, worker.jobCount)
        updateWorkerStatus(worker.id, worker.status)
    }
}