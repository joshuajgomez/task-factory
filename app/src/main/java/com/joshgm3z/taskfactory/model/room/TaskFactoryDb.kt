package com.joshgm3z.taskfactory.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.joshgm3z.taskfactory.model.room.dao.TaskDao
import com.joshgm3z.taskfactory.model.room.dao.WorkerDao
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker

@Database(entities = [Task::class, Worker::class], version = 1)
abstract class TaskFactoryDb : RoomDatabase() {

    abstract fun taskDao(): TaskDao
    abstract fun workerDao(): WorkerDao

    companion object {
        fun getInstance(context: Context): TaskFactoryDb =
            Room.databaseBuilder(
                context,
                TaskFactoryDb::class.java,
                "task-factory-db"
            )
                .build()

    }
}