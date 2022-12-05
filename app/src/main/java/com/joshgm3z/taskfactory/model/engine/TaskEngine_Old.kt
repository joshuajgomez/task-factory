package com.joshgm3z.taskfactory.model.engine

import android.os.CountDownTimer
import android.util.Log
import com.joshgm3z.taskfactory.utils.Logger
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker
import java.util.concurrent.CopyOnWriteArrayList

class TaskEngine_Old(
    private val mCallback: TaskEngineCallback,
) {

    companion object {
        const val WORKING_INTERVAL: Long = 1000
    }

    private val mMaxActiveJob = 3
    private val mWorkingTime: Long = 4000
    private val mActiveTaskList: CopyOnWriteArrayList<ActiveTask> = CopyOnWriteArrayList()
    private var mCurrentTasks: ArrayList<Task> = ArrayList()
    private var mCurrentWorkers: ArrayList<Worker> = ArrayList()

    init {
        Logger.entryLog()
        object : CountDownTimer(mWorkingTime, WORKING_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                Logger.entryLog()
                // work
                doWork()
            }

            override fun onFinish() {
                // do nothing
                start()
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

                    activeTask.task.status = Task.STATUS_FINISHED
                    activeTask.worker.status = Worker.STATUS_IDLE
                    activeTask.worker.jobCount++
                    mCallback.onTaskFinish(activeTask)

                    updateActiveTasks()
                }
            }
        }
        Logger.log("mActiveTaskList=[$mActiveTaskList]")
    }

    fun notifyOnTaskUpdate(taskList: List<Task>?) {
        Logger.log("taskList=[$taskList]")
        mCurrentTasks.clear()
        mCurrentTasks.addAll(taskList!!.filter { task -> (task.status == Task.STATUS_ADDED) })
        updateActiveTasks()
        Logger.log(Log.DEBUG, "mCurrentTasks=[$mCurrentTasks]")
    }

    fun notifyOnWorkerUpdate(workerList: List<Worker>?) {
        Logger.log("workerList=[$workerList]")
        mCurrentWorkers.clear()
        mCurrentWorkers.addAll(workerList!!.filter { worker -> worker.status == Worker.STATUS_IDLE })
        mCurrentWorkers.sortBy { worker -> worker.jobCount }
        updateActiveTasks()
        Logger.log(Log.DEBUG, "mCurrentWorkers=[$mCurrentWorkers]")
    }

    private fun updateActiveTasks() {
        Logger.entryLog()
        if (mActiveTaskList.size < mMaxActiveJob
            && mCurrentTasks.isNotEmpty()
            && mCurrentWorkers.isNotEmpty()
        ) {

            val worker = mCurrentWorkers.removeFirst()
            worker.status = Worker.STATUS_BUSY
            val task = mCurrentTasks.removeFirst()
            task.status = Task.STATUS_ONGOING
            task.activeWorkerName = worker.name

            val activeTask = ActiveTask(task, worker, task.duration)
            mActiveTaskList.add(activeTask)

            // notify UI
            mCallback.onTaskStart(activeTask)
        } else {
            // tasks or workers not ready
        }
        Logger.log("mActiveTaskList=[$mActiveTaskList]")
    }

}