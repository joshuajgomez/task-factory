package com.joshgm3z.taskfactory.model.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joshgm3z.taskfactory.model.room.dao.ActivityLogDao
import com.joshgm3z.taskfactory.model.room.dao.TaskDao
import com.joshgm3z.taskfactory.model.room.dao.WorkerDao
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker

@Database(entities = [Task::class, Worker::class, ActivityLog::class], version = 4, exportSchema = false)
abstract class TaskFactoryDb : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    abstract fun workerDao(): WorkerDao

    abstract fun activityLogDao(): ActivityLogDao

}