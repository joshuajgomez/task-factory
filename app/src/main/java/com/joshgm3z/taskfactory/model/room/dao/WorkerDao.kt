package com.joshgm3z.taskfactory.model.room.dao

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
    fun getAll(): List<Worker>
}