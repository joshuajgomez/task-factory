package com.joshgm3z.taskfactory.view.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joshgm3z.taskfactory.R
import com.joshgm3z.taskfactory.common.utils.Logger
import com.joshgm3z.taskfactory.model.room.entity.Task
import javax.inject.Inject
import kotlin.collections.ArrayList

class TaskAdapter @Inject constructor() : RecyclerView.Adapter<TaskViewHolder>() {

    private var mTaskList: ArrayList<Task> = ArrayList()

    fun updateTaskList(taskList: List<Task>) {
        Logger.entryLog()
        mTaskList.clear()
        mTaskList.addAll(taskList)
        mTaskList.sortBy { task: Task -> task.status }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.layout_item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.updateData(mTaskList[position])
    }

    override fun getItemCount(): Int {
        return mTaskList.size
    }

}