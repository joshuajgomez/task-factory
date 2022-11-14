package com.joshgm3z.taskfactory.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.joshgm3z.taskfactory.common.utils.RandomData
import com.joshgm3z.taskfactory.model.TaskRepository
import com.joshgm3z.taskfactory.model.engine.ActiveTask
import com.joshgm3z.taskfactory.model.engine.TaskEngine
import com.joshgm3z.taskfactory.model.engine.TaskEngineCallback
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker

class DashboardViewModel() : ViewModel(), TaskEngineCallback {

    private var mActiveTaskList: ArrayList<ActiveTask>? = null
    private var repo: TaskRepository? = null
    private val mTaskEngine: TaskEngine = TaskEngine(this)

    var mTaskList: LiveData<List<Task>>? = null
    var mWorkerList: LiveData<List<Worker>>? = null
    var mActivityLogList: LiveData<List<ActivityLog>>? = null

    fun createRepository(applicationContext: Context) {
        repo = TaskRepository(applicationContext)
        mTaskList = repo!!.getAllTasks()
        mWorkerList = repo!!.getAllWorkers()
        mActivityLogList = repo!!.getActivityLog()

        mTaskList.observe(this, )
    }

    fun addMockTask() {
        val description = RandomData.getTaskName()
        repo?.addTask(description, RandomData.getTaskDuration())
        logActivity("New task added: $description")
    }

    fun addMockWorker() {
        val name = RandomData.getWorkerName()
        repo?.addWorker(name)
        logActivity("Worker recruited: $name")
    }

    fun logActivity(description: String){
        repo?.addActivityLog(description)
    }

    override fun onActiveTaskChanged(activeTaskList: ArrayList<ActiveTask>) {
        mActiveTaskList = activeTaskList
    }

    override fun onTaskStart(activeTask: ActiveTask) {
        repo!!.updateTaskStatus(activeTask.taskId, Task.STATUS_ONGOING)
        repo!!.updateWorkerStatus(activeTask.workerId, Worker.STATUS_BUSY)
        repo!!.addActivityLog("${activeTask.workerId} started working on ${activeTask.taskId}")
    }

    override fun onTaskFinish(activeTask: ActiveTask) {
        repo!!.updateTaskStatus(activeTask.taskId, Task.STATUS_FINISHED)
        repo!!.updateWorkerStatus(activeTask.workerId, Worker.STATUS_IDLE)
        repo!!.addActivityLog("${activeTask.workerId} finished ${activeTask.taskId}")
    }

}