package com.joshgm3z.taskfactory.model.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.joshgm3z.taskfactory.model.room.entity.Worker

@Dao
interface WorkerDao {

    @Insert
    fun insert(worker: Worker)

    @Update
    fun update(worker: Worker)

    @Query("select * from Worker")
    fun getAll(): LiveData<List<Worker>>

    @Query("update Worker set status=:status where id=:workerId")
    fun updateStatus(workerId: Int, status: Int)

    @Query("update Worker set job_count=job_count+1 where id=:workerId")
    fun incrementJobCount(workerId: Int)

    @Query("delete from Worker")
    fun clear()
}