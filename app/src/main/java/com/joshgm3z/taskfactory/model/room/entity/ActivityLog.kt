package com.joshgm3z.taskfactory.model.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ActivityLog(@ColumnInfo(name = "description") var description: String,
                  @ColumnInfo(name = "date_finished") var dateFinished: Long = 0
) {

    @PrimaryKey(autoGenerate = true)
    var id = 0

    override fun toString(): String {
        return "ActivityLog(description='$description', dateFinished=$dateFinished, id=$id)"
    }


}