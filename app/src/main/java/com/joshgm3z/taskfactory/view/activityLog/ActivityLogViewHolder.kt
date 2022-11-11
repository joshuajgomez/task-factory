package com.joshgm3z.taskfactory.view.activityLog

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joshgm3z.taskfactory.R
import com.joshgm3z.taskfactory.common.utils.DateUtil
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Task

class ActivityLogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mTvTime: TextView = itemView.findViewById(R.id.tv_time)
    private val mTvDescription: TextView = itemView.findViewById(R.id.tv_description)

    fun updateData(activityLog: ActivityLog) {
        mTvDescription.text = activityLog.description
        mTvTime.text = DateUtil.getPrettyDate(activityLog.dateFinished)
    }
}