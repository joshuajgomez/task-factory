package com.joshgm3z.taskfactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joshgm3z.taskfactory.model.engine.ActiveTask
import com.joshgm3z.taskfactory.model.engine.TaskEngine_Old
import com.joshgm3z.taskfactory.model.engine.TaskEngineCallback
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker
import com.joshgm3z.taskfactory.view.activityLog.ActivityLogAdapter
import com.joshgm3z.taskfactory.view.task.TaskAdapter
import com.joshgm3z.taskfactory.view.worker.WorkerAdapter
import com.joshgm3z.taskfactory.viewmodel.DashboardViewModel
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : AppCompatActivity(), TaskEngineCallback {

    private val mEngine = TaskEngine_Old(this)

    private val mViewModel by viewModel<DashboardViewModel>()

    private val mTaskAdapter = get<TaskAdapter>()

    private val mWorkerAdapter = get<WorkerAdapter>()

    private val mActivityLogAdapter = get<ActivityLogAdapter>()

    private val mRvTaskList: RecyclerView by lazy {
        findViewById(R.id.rv_task_list)
    }
    private val mTvAddTask: TextView by lazy {
        findViewById(R.id.tv_add_task)
    }
    private val mRvWorkerList: RecyclerView by lazy {
        findViewById(R.id.rv_worker_list)
    }
    private val mTvRecruitWorker: TextView by lazy {
        findViewById(R.id.tv_recruit_worker)
    }

    private val mRvActivityLogList: RecyclerView by lazy {
        findViewById(R.id.rv_activity_list)
    }
    private val mTvTaskCount: TextView by lazy {
        findViewById(R.id.tv_task_count)
    }
    private val mTvWorkerCount: TextView by lazy {
        findViewById(R.id.tv_worker_count)
    }
    private val mIvTaskOptions: ImageView by lazy {
        findViewById(R.id.iv_task_options)
    }
    private val mIvWorkerOptions: ImageView by lazy {
        findViewById(R.id.iv_worker_options)
    }
    private val mIvActivityOptions: ImageView by lazy {
        findViewById(R.id.iv_activity_options)
    }
    private val mTvEmptyTasks: TextView by lazy {
        findViewById(R.id.tv_empty_tasks)
    }
    private val mTvEmptyWorkers: TextView by lazy {
        findViewById(R.id.tv_empty_worker)
    }
    private val mTvEmptyLog: TextView by lazy {
        findViewById(R.id.tv_empty_log)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

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

        val linearLayoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, true)
        linearLayoutManager.stackFromEnd = true
        mRvActivityLogList.layoutManager = linearLayoutManager
        mRvActivityLogList.adapter = mActivityLogAdapter

        mIvWorkerOptions.setOnClickListener { mViewModel.clearWorkerList() }
        mIvTaskOptions.setOnClickListener { mViewModel.clearTaskList() }
        mIvActivityOptions.setOnClickListener { mViewModel.clearActivityLog() }
    }

    private fun initObservers() {
        val taskObserver = Observer<List<Task>> {
            mTaskAdapter.updateTaskList(it)
            mTvTaskCount.text = "(${it.size})"
            mEngine.notifyOnTaskUpdate(it)
            if (it.isEmpty()) {
                mRvTaskList.visibility = View.GONE
                mTvEmptyTasks.visibility = View.VISIBLE
            } else {
                mRvTaskList.visibility = View.VISIBLE
                mTvEmptyTasks.visibility = View.GONE
            }
        }
        mViewModel.mTaskList!!.observe(this, taskObserver)

        val workerObserver = Observer<List<Worker>> {
            mWorkerAdapter.updateWorkerList(it)
            mTvWorkerCount.text = "(${it.size})"
            mEngine.notifyOnWorkerUpdate(it)
            if (it.isEmpty()) {
                mRvWorkerList.visibility = View.GONE
                mTvEmptyWorkers.visibility = View.VISIBLE
            } else {
                mRvWorkerList.visibility = View.VISIBLE
                mTvEmptyWorkers.visibility = View.GONE
            }
        }
        mViewModel.mWorkerList!!.observe(this, workerObserver)

        val activityObserver = Observer<List<ActivityLog>> {
            mActivityLogAdapter.updateActivityLogList(it)
            if (it.isNotEmpty()) mRvActivityLogList.smoothScrollToPosition(it.size - 1)
            if (it.isEmpty()) {
                mRvActivityLogList.visibility = View.GONE
                mTvEmptyLog.visibility = View.VISIBLE
            } else {
                mRvActivityLogList.visibility = View.VISIBLE
                mTvEmptyLog.visibility = View.GONE
            }
        }
        mViewModel.mActivityLogList!!.observe(this, activityObserver)
    }

    override fun onTaskStart(activeTask: ActiveTask) {
        mViewModel.onTaskStart(activeTask)
        mTaskAdapter.notifyStatusChange(activeTask.task)
        mWorkerAdapter.notifyStatusChange(activeTask.worker)
        mRvTaskList.scrollToPosition(0)
        mRvWorkerList.scrollToPosition(0)
    }

    override fun onTaskFinish(activeTask: ActiveTask) {
        mViewModel.onTaskFinish(activeTask)
        mTaskAdapter.notifyStatusChange(activeTask.task)
        mWorkerAdapter.notifyStatusChange(activeTask.worker)
        mRvTaskList.scrollToPosition(0)
        mRvWorkerList.scrollToPosition(0)
    }
}