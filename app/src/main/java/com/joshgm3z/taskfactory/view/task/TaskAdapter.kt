package com.joshgm3z.taskfactory.view.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joshgm3z.taskfactory.R
import com.joshgm3z.taskfactory.common.utils.Logger
import com.joshgm3z.taskfactory.model.room.entity.Task
import kotlin.collections.ArrayList

class TaskAdapter : RecyclerView.Adapter<TaskViewHolder>() {

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
            var indexOf = mTaskList.indexOfFirst { it.status == Task.STATUS_FINISHED }
            if (indexOf == -1)
                indexOf = mTaskList.size
            mTaskList.add(indexOf, taskList.last())
            notifyItemInserted(indexOf)
        }
    }

    fun notifyStatusChange(task: Task) {
        Logger.log("task = [${task}]")
        val indexOf = mTaskList.indexOfFirst { it.id == task.id }
        mTaskList.removeAt(indexOf)

        var toPosition =
            if (task.status == Task.STATUS_ONGOING) 0
            else mTaskList.indexOfFirst { it.status == Task.STATUS_FINISHED }
        if (toPosition == -1) toPosition = mTaskList.size

        mTaskList.add(toPosition, task)
        notifyItemMoved(indexOf, toPosition)
        notifyItemChanged(toPosition)
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