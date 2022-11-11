package com.joshgm3z.taskfactory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.view.task.TaskAdapter
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        mViewModel.createRepository(applicationContext)
//        mViewModel.addMockTask()

        initUI()
        initObservers()
    }

    private fun initUI() {
        mRvTaskList.layoutManager = LinearLayoutManager(applicationContext)
        mRvTaskList.adapter = mTaskAdapter

        mTvAddTask.setOnClickListener(View.OnClickListener { mViewModel.addMockTask() })
    }

    private fun initObservers() {
        val taskObserver = Observer<List<Task>> {
            mTaskAdapter.updateTaskList(it)
        }
        mViewModel.mTaskList!!.observe(this, taskObserver)
    }
}