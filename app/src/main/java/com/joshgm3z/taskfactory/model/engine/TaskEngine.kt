package com.joshgm3z.taskfactory.model.engine

import android.os.CountDownTimer
import android.util.Log
import com.joshgm3z.taskfactory.common.utils.Logger
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker

class TaskEngine(private val mCallback: TaskEngineCallback) {

    private val tag = "TaskEngine"

    private val mMaxActiveJob = 3
    private val mWorkingInterval: Long = 1000
    private val mWorkingTime: Long = 30000
    private val mActiveTaskList: ArrayList<ActiveTask> = ArrayList()
    private var mCurrentTasks: List<Task>? = null
    private var mCurrentWorkers: List<Worker>? = null

    init {
        Logger.entryLog()
//        initActiveTasks()
        object : CountDownTimer(mWorkingTime, mWorkingInterval) {
            override fun onTick(millisUntilFinished: Long) {
                Logger.entryLog()
                // work
                doWork()
            }

            override fun onFinish() {
                // do nothing
            }
        }.start()
    }

    private fun doWork() {
        Logger.entryLog()
        if (mActiveTaskList.isNotEmpty()) {
            for (activeTask in mActiveTaskList) {
                activeTask.timeLeft--
                if (activeTask.timeLeft == 0) {
                    mActiveTaskList.remove(activeTask)
                    mCallback.onTaskFinish(activeTask)

                    updateActiveTasks()
                }
            }
        }
        Logger.log("mActiveTaskList=[$mActiveTaskList]")
    }

    fun notifyOnTaskUpdate(taskList: List<Task>?) {
        Logger.log("taskList=[$taskList]")
        mCurrentTasks = getIdleTasks(taskList)
        Logger.log("mCurrentTasks=[$mCurrentTasks]")
        updateActiveTasks()
    }

    private fun updateActiveTasks() {
        Logger.entryLog()
        if (mActiveTaskList.size < mMaxActiveJob
            && mCurrentTasks!!.isNotEmpty()
            && mCurrentWorkers!!.isNotEmpty()) {

            val task = mCurrentTasks!!.first()
            val worker = mCurrentWorkers!!.first()
            val activeTask = ActiveTask(task.id, worker.id, task.duration)
            mActiveTaskList.add(activeTask)

            mCallback.onTaskStart(activeTask)
            // notify UI
            mCallback.onActiveTaskChanged(mActiveTaskList)
        } else {
            // tasks or workers not ready
        }
        Logger.log("mActiveTaskList=[$mActiveTaskList]")
    }

    fun notifyOnWorkerUpdate(workerList: List<Worker>?) {
        Logger.log("workerList=[$workerList]")
        mCurrentWorkers = getIdleWorkers(workerList)
        Logger.log("mCurrentWorkers=[$mCurrentWorkers]")
        updateActiveTasks()
    }

    fun getIdleWorkers(workerList: List<Worker>?): List<Worker>? =
        workerList!!.filter { worker -> worker.status != Worker.STATUS_BUSY }

    fun getIdleTasks(taskList: List<Task>?): List<Task>? =
        taskList!!.filter { task -> task.status != Task.STATUS_ONGOING }

}