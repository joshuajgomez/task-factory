package com.joshgm3z.taskfactory.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Task(
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "time_added") val timeAdded: Long,
    @ColumnInfo(name = "duration") val duration: Long
) {

    @PrimaryKey(autoGenerate = true)
    val id = 0

    @ColumnInfo(name = "status")
    val status = 0

    @ColumnInfo(name = "time_completed")
    val timeCompleted: Long = 0

    @ColumnInfo(name = "active_worker")
    val activeWorker: Worker = TODO()
}