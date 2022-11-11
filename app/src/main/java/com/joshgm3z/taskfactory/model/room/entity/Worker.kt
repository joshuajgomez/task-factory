package com.joshgm3z.taskfactory.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Worker(@ColumnInfo(name = "name") var name: String) {

    @PrimaryKey(autoGenerate = true)
    var id = 0

    @ColumnInfo(name = "job_count")
    var jobCount = 0

    @ColumnInfo(name = "status")
    var status = 0

}