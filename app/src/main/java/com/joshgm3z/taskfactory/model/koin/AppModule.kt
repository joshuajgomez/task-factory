package com.joshgm3z.taskfactory.model.koin

import androidx.room.Room
import com.joshgm3z.taskfactory.model.TaskRepository
import com.joshgm3z.taskfactory.model.room.TaskFactoryDb
import com.joshgm3z.taskfactory.viewmodel.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        Room.databaseBuilder(
            get(),
            TaskFactoryDb::class.java, "task-factory-db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        TaskRepository(get())
    }
    viewModel {
        TaskViewModel(get())
    }
}