package com.joshgm3z.taskfactory.view.task

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joshgm3z.taskfactory.R
import com.joshgm3z.taskfactory.common.utils.DateUtil
import com.joshgm3z.taskfactory.model.room.entity.Task

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mTvTaskName: TextView = itemView.findViewById(R.id.tv_task_name)
    private val mTvTimeAdded: TextView = itemView.findViewById(R.id.tv_time)
    private val mTvTaskDuration: TextView = itemView.findViewById(R.id.tv_duration)
    private val mTvStatus: TextView = itemView.findViewById(R.id.tv_status)
    private val mTvTypeTag: TextView = itemView.findViewById(R.id.tv_type_tag)
    private val mIvStatusIcon: ImageView = itemView.findViewById(R.id.iv_task_status)
    private val mPbStatusIcon: ProgressBar = itemView.findViewById(R.id.pb_task_progress)
    private val mLlProgressContainer: LinearLayout =
        itemView.findViewById(R.id.ll_progress_container)

    fun updateData(task: Task) {
        mTvTaskName.text = task.description
        mTvTimeAdded.text = DateUtil.getPrettyDate(task.timeAdded)
        mTvTaskDuration.text = task.duration.toString()
        mTvTypeTag.text = task.getTypeText()
        mTvTypeTag.setBackgroundResource(
            when (task.type) {
                Task.TYPE_COOKING -> R.color.yellow
                Task.TYPE_CLEANING -> R.color.green
                Task.TYPE_HELPING -> R.color.blue
                Task.TYPE_MAINTENANCE -> R.color.red
                Task.TYPE_BUSINESS -> R.color.purple
                else -> R.color.white
            }
        )
        when (task.status) {
            Task.STATUS_ADDED -> {
                // new task
                mLlProgressContainer.visibility = View.GONE
                mPbStatusIcon.visibility = View.GONE
                mIvStatusIcon.visibility = View.VISIBLE
                mIvStatusIcon.setImageResource(R.drawable.ic_hour_glass)
            }
            Task.STATUS_ONGOING -> {
                // ongoing task
                mTvStatus.text = "In progress by " + task.activeWorkerName
                mLlProgressContainer.visibility = View.VISIBLE
                mIvStatusIcon.setImageResource(R.drawable.ic_rotate_blue)
                mPbStatusIcon.visibility = View.VISIBLE
                mIvStatusIcon.visibility = View.GONE
//                startDurationCountDown(task.duration)
            }
            Task.STATUS_FINISHED -> {
                // finished task
                mPbStatusIcon.visibility = View.GONE
                mIvStatusIcon.visibility = View.VISIBLE
                mLlProgressContainer.visibility = View.GONE
                mIvStatusIcon.setImageResource(R.drawable.ic_tick)
                mTvTaskDuration.text = task.duration.toString()
            }
        }

    }
}