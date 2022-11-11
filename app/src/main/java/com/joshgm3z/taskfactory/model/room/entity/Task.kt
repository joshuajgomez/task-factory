package com.joshgm3z.taskfactory.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Task(
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "time_added") var timeAdded: Long,
    @ColumnInfo(name = "duration") var duration: Long
) {

    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "status")
    var status = 0

    @ColumnInfo(name = "time_completed")
    var timeCompleted: Long = 0

    @ColumnInfo(name = "active_worker_id")
    var activeWorkerId: Int = -1
}