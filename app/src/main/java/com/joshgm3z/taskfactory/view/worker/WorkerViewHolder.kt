package com.joshgm3z.taskfactory.view.worker

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joshgm3z.taskfactory.R
import com.joshgm3z.taskfactory.model.room.entity.Worker

class WorkerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mTvWorkerName: TextView = itemView.findViewById(R.id.tv_worker_name)
    private val mTvJobCount: TextView = itemView.findViewById(R.id.tv_job_count)
    private val mIvStatus: ImageView = itemView.findViewById(R.id.iv_status)
    private val mPbStatus: ProgressBar = itemView.findViewById(R.id.pb_worker_progress)

    fun updateData(worker: Worker) {
        mTvWorkerName.text = worker.name
        mTvJobCount.text = worker.jobCount.toString()
        when (worker.status) {
            Worker.STATUS_BUSY -> {
                mPbStatus.visibility = View.VISIBLE
                mIvStatus.visibility = View.INVISIBLE
            }
            Worker.STATUS_IDLE -> {
                mPbStatus.visibility = View.INVISIBLE
                mIvStatus.visibility = View.VISIBLE
            }
        }
    }
}