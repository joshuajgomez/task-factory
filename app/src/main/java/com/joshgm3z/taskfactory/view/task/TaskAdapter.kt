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
        if (taskList.isEmpty()) {
            mTaskList.clear()
            notifyDataSetChanged()
        } else if (mTaskList.isEmpty()) {
            mTaskList.addAll(taskList)
            notifyItemRangeInserted(0, mTaskList.size)
        } else if (mTaskList.size != taskList.size) {
            mTaskList.add(taskList.last())
            notifyItemInserted(mTaskList.size - 1)
//            mTaskList.addAll(taskList.filter { task: Task -> task.status == Task.STATUS_ONGOING })
//            mTaskList.addAll(taskList.filter { task: Task -> task.status == Task.STATUS_ADDED })
//            mTaskList.addAll(taskList.filter { task: Task -> task.status == Task.STATUS_FINISHED })
//            notifyDataSetChanged()
        }
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

    fun notifyStatusChange(task: Task) {
        var indexOf = -1
        mTaskList.forEachIndexed { index, _task -> if (task.id == _task.id) indexOf = index }
        Logger.log(
            "task.id = [${task.id}], task.name = [${task.description}], " +
                    "task.status = [${task.getStatusText()}], indexOf = [${indexOf}]"
        )
        mTaskList[indexOf] = task
        notifyItemChanged(indexOf)
    }

}