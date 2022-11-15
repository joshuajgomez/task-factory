package com.joshgm3z.taskfactory.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Task(
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "time_added") var timeAdded: Long,
    @ColumnInfo(name = "duration") var duration: Int
) {

    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "status")
    var status = STATUS_ADDED

    @ColumnInfo(name = "time_completed")
    var timeCompleted: Long = 0

    @ColumnInfo(name = "active_worker_name")
    var activeWorkerName: String = ""

    companion object {
        const val STATUS_ADDED = 0
        const val STATUS_ONGOING = 1
        const val STATUS_FINISHED = 2
    }

    override fun toString(): String {
        return "Task(description='$description', timeAdded=$timeAdded, duration=$duration," +
                " id=$id, status=$status, timeCompleted=$timeCompleted," +
                " activeWorkerName=$activeWorkerName)"
    }


}