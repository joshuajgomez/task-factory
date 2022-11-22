package com.joshgm3z.taskfactory.model.hilt

import android.content.Context
import androidx.room.Room
import com.joshgm3z.taskfactory.model.room.TaskFactoryDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class TaskModule {

    @Provides
    fun providesDb(@ApplicationContext context: Context): TaskFactoryDb = Room.databaseBuilder(
        context,
        TaskFactoryDb::class.java, "task-factory-db"
    ).fallbackToDestructiveMigration()
        .build()

}