package com.joshgm3z.taskfactory.view.worker

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joshgm3z.taskfactory.R
import com.joshgm3z.taskfactory.common.utils.DateUtil
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker

class WorkerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mTvWorkerName: TextView = itemView.findViewById(R.id.tv_worker_name)
    private val mTvJobCount: TextView = itemView.findViewById(R.id.tv_job_count)
    private val mIvStatus: ImageView = itemView.findViewById(R.id.iv_status)

    fun updateData(worker: Worker) {
        mTvWorkerName.text = worker.name
        mTvJobCount.text = worker.jobCount.toString()
        when (worker.status) {
            Worker.STATUS_BUSY -> {
                mIvStatus.setImageResource(R.drawable.ic_rotate_blue)
            }
            Worker.STATUS_IDLE -> {
                mIvStatus.setImageResource(R.drawable.ic_hour_glass)
            }
        }
    }
}