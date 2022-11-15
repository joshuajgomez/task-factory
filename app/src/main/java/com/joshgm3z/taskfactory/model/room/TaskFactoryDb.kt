package com.joshgm3z.taskfactory.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.joshgm3z.taskfactory.model.room.dao.ActivityLogDao
import com.joshgm3z.taskfactory.model.room.dao.TaskDao
import com.joshgm3z.taskfactory.model.room.dao.WorkerDao
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker

@Database(entities = [Task::class, Worker::class, ActivityLog::class], version = 3)
abstract class TaskFactoryDb : RoomDatabase() {

    abstract fun taskDao(): TaskDao
    abstract fun workerDao(): WorkerDao
    abstract fun activityLogDao(): ActivityLogDao

    companion object {
        private var db: TaskFactoryDb? = null

        fun getInstance(context: Context): TaskFactoryDb {
            if (db == null)
                db = Room.databaseBuilder(
                    context,
                    TaskFactoryDb::class.java, "task-factory-db"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            return db as TaskFactoryDb
        }

    }
}