package com.joshgm3z.taskfactory.view.task

import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joshgm3z.taskfactory.R
import com.joshgm3z.taskfactory.common.utils.DateUtil
import com.joshgm3z.taskfactory.common.utils.Logger
import com.joshgm3z.taskfactory.model.engine.TaskEngine
import com.joshgm3z.taskfactory.model.room.entity.Task

class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mTvTaskName: TextView = itemView.findViewById(R.id.tv_task_name)
    private val mTvTimeAdded: TextView = itemView.findViewById(R.id.tv_time)
    private val mTvTaskDuration: TextView = itemView.findViewById(R.id.tv_duration)
    private val mTvStatus: TextView = itemView.findViewById(R.id.tv_status)
    private val mIvStatusIcon: ImageView = itemView.findViewById(R.id.iv_task_status)
    private val mLlProgressContainer: LinearLayout =
        itemView.findViewById(R.id.ll_progress_container)

    fun updateData(task: Task) {
        mTvTaskName.text = task.description
        mTvTimeAdded.text = DateUtil.getPrettyDate(task.timeAdded)
        mTvTaskDuration.text = task.duration.toString()
        when (task.status) {
            Task.STATUS_ADDED -> {
                // new task
                mLlProgressContainer.visibility = View.GONE
                mIvStatusIcon.setImageResource(R.drawable.ic_hour_glass)
            }
            Task.STATUS_ONGOING -> {
                // ongoing task
                mTvStatus.text = "In progress by " + task.activeWorkerName
                mLlProgressContainer.visibility = View.VISIBLE
                mIvStatusIcon.setImageResource(R.drawable.ic_rotate_blue)
//                startDurationCountDown(task.duration)
            }
            Task.STATUS_FINISHED -> {
                // finished task
                mLlProgressContainer.visibility = View.GONE
                mIvStatusIcon.setImageResource(R.drawable.ic_tick)
                mTvTaskDuration.text = task.duration.toString()
            }
        }

    }

    private fun startDurationCountDown(duration: Int) {
        object : CountDownTimer((duration * 1000).toLong(), TaskEngine.WORKING_INTERVAL) {
            override fun onTick(millisUntilFinished: Long) {
                mTvTaskDuration.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                // do nothing
            }
        }.start()
    }
}