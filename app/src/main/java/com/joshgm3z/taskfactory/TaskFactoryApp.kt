package com.joshgm3z.taskfactory

import android.app.Application
import com.joshgm3z.taskfactory.model.koin.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TaskFactoryApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TaskFactoryApp)
            modules(appModule)
        }
    }
}