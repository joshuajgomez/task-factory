package com.joshgm3z.taskfactory.view.task

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joshgm3z.taskfactory.R
import com.joshgm3z.taskfactory.model.room.entity.Task

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mTvTaskName: TextView = itemView.findViewById(R.id.tv_task_name)
    private val mTvTimeAdded: TextView = itemView.findViewById(R.id.tv_time)
    private val mTvTaskDuration: TextView = itemView.findViewById(R.id.tv_duration)
    private val mTvStatus: TextView = itemView.findViewById(R.id.tv_status)
    private val mIvStatusIcon: ImageView = itemView.findViewById(R.id.iv_status_icon)
    private val mLlProgressContainer: LinearLayout = itemView.findViewById(R.id.ll_progress_container)

    fun updateData(task: Task) {
        mTvTaskName.text = task.description
        mTvTimeAdded.text = task.timeAdded.toString()
        mTvTaskDuration.text = task.duration.toString()
        if (task.activeWorkerId != -1) {
            // task ongoing
            mLlProgressContainer.visibility = View.GONE
        } else {
            // inactive task
            mLlProgressContainer.visibility = View.VISIBLE
            mTvStatus.text = "In progress by " + task.activeWorkerId
        }
    }
}