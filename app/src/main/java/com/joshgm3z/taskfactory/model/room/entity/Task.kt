package com.joshgm3z.taskfactory.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Task(
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "time_added") var timeAdded: Long,
    @ColumnInfo(name = "duration") var duration: Int,
    @ColumnInfo(name = "type") var type: Int,
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

        const val TYPE_COOKING = 0
        const val TYPE_CLEANING = 1
        const val TYPE_HELPING = 2
        const val TYPE_MAINTENANCE = 3
        const val TYPE_BUSINESS = 4
        const val TYPE_ERRAND = 5

        fun getTypeText(type: Int): String = when (type) {
            TYPE_COOKING -> "Cooking"
            TYPE_CLEANING -> "Cleaning"
            TYPE_HELPING -> "Helping"
            TYPE_MAINTENANCE -> "Maintenance"
            TYPE_BUSINESS -> "Business"
            TYPE_ERRAND -> "Errand"
            else -> "UNKNOWN"
        }
    }

    fun getStatusText(): String = when (status) {
        STATUS_ADDED -> "ADDED"
        STATUS_ONGOING -> "ONGOING"
        STATUS_FINISHED -> "FINISHED"
        else -> "UNKNOWN"
    }

    fun getTypeText(): String = getTypeText(type)

    override fun toString(): String {
        return "Task(id=$id, status=${getStatusText()}, type=${getTypeText()}," +
                " description='$description'," +
                " timeAdded=$timeAdded, duration=$duration," +
                " timeCompleted=$timeCompleted," +
                " activeWorkerName=$activeWorkerName)"
    }


}