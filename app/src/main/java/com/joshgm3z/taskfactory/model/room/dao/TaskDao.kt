package com.joshgm3z.taskfactory.model.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.joshgm3z.taskfactory.model.room.entity.Task

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

    @Query("update Worker set job_count=job_count+1 where id=:workerId")
    suspend fun incrementWorkerJobCount(workerId: Int)

    @Transaction
    suspend fun runTaskStartTransaction(
        taskId: Int,
        taskStatus: Int,
        workerId: Int,
        workerName: String,
        workerStatus: Int
    ) {
        updateTaskStatus(taskId, taskStatus, workerName)
        updateWorkerStatus(workerId, workerStatus)
    }

    @Transaction
    suspend fun runTaskFinishTransaction(
        taskId: Int,
        taskStatus: Int,
        workerId: Int,
        workerName: String,
        workerStatus: Int
    ) {
        updateTaskStatus(taskId, taskStatus, workerName)
        incrementWorkerJobCount(workerId)
        updateWorkerStatus(workerId, workerStatus)
    }
}