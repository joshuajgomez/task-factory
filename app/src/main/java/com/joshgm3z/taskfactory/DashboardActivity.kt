package com.joshgm3z.taskfactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker
import com.joshgm3z.taskfactory.view.activityLog.ActivityLogAdapter
import com.joshgm3z.taskfactory.view.task.TaskAdapter
import com.joshgm3z.taskfactory.view.worker.WorkerAdapter
import com.joshgm3z.taskfactory.viewmodel.DashboardViewModel

class DashboardActivity : AppCompatActivity() {

    private val mViewModel: DashboardViewModel by viewModels()
    private val mTaskAdapter: TaskAdapter = TaskAdapter()
    private val mRvTaskList: RecyclerView by lazy {
        findViewById(R.id.rv_task_list)
    }
    private val mTvAddTask: TextView by lazy {
        findViewById(R.id.tv_add_task)
    }
    private val mWorkerAdapter: WorkerAdapter = WorkerAdapter()
    private val mRvWorkerList: RecyclerView by lazy {
        findViewById(R.id.rv_worker_list)
    }
    private val mTvRecruitWorker: TextView by lazy {
        findViewById(R.id.tv_recruit_worker)
    }
    private val mActivityLogAdapter: ActivityLogAdapter = ActivityLogAdapter()
    private val mRvActivityLogList: RecyclerView by lazy {
        findViewById(R.id.rv_activity_list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        mViewModel.createRepository(applicationContext)

        initUI()
        initObservers()
    }

    private fun initUI() {
        mRvTaskList.layoutManager = LinearLayoutManager(applicationContext)
        mRvTaskList.adapter = mTaskAdapter
        mTvAddTask.setOnClickListener { mViewModel.addMockTask() }

        mRvWorkerList.layoutManager = LinearLayoutManager(applicationContext)
        mRvWorkerList.adapter = mWorkerAdapter
        mTvRecruitWorker.setOnClickListener { mViewModel.addMockWorker() }

        mRvActivityLogList.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, true)
        mRvActivityLogList.adapter = mActivityLogAdapter
    }

    private fun initObservers() {
        val taskObserver = Observer<List<Task>> {
            mTaskAdapter.updateTaskList(it)
        }
        mViewModel.mTaskList!!.observe(this, taskObserver)

        val workerObserver = Observer<List<Worker>> {
            mWorkerAdapter.updateWorkerList(it)
        }
        mViewModel.mWorkerList!!.observe(this, workerObserver)

        val activityObserver = Observer<List<ActivityLog>> {
            mActivityLogAdapter.updateActivityLogList(it)
        }
        mViewModel.mActivityLogList!!.observe(this, activityObserver)
    }
}