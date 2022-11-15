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
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Query("select * from Task")
    fun getAll(): LiveData<List<Task>>

    @Query("update Task set status=:status, active_worker_name=:workerName where id=:taskId")
    fun updateStatus(taskId: Int, status: Int, workerName: String)

    @Query("delete from Task")
    fun clear()
}