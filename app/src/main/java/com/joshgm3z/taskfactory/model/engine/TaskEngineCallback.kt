package com.joshgm3z.taskfactory.model.engine

interface TaskEngineCallback {
    fun onTaskStart(activeTask: ActiveTask)
    fun onTaskFinish(activeTask: ActiveTask)
}