package com.joshgm3z.taskfactory.model.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
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
    suspend fun updateStatus(taskId: Int, status: Int, workerName: String)

    @Query("delete from Task")
    suspend fun clear()
}