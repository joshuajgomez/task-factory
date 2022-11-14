package com.joshgm3z.taskfactory.model.engine

interface TaskEngineCallback {
    fun onActiveTaskChanged(activeTaskList: ArrayList<ActiveTask>)
    fun onTaskStart(activeTask: ActiveTask)
    fun onTaskFinish(activeTask: ActiveTask)
}