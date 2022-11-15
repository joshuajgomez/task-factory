package com.joshgm3z.taskfactory.model.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Worker

@Dao
interface ActivityLogDao {

    @Insert
    suspend fun insert(activity: ActivityLog)

    @Query("select * from ActivityLog")
    fun getAll(): LiveData<List<ActivityLog>>

    @Query("delete from ActivityLog")
    suspend fun clear()
}