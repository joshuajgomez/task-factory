package com.joshgm3z.taskfactory.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Worker(@ColumnInfo(name = "name") val name: String) {

    @PrimaryKey(autoGenerate = true)
    val id = 0

    @ColumnInfo(name = "job_count")
    val jobCount = 0

    @ColumnInfo(name = "status")
    val status = 0

}