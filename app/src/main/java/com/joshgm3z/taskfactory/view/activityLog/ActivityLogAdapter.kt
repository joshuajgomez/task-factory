package com.joshgm3z.taskfactory.view.activityLog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joshgm3z.taskfactory.R
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import javax.inject.Inject

class ActivityLogAdapter @Inject constructor()  : RecyclerView.Adapter<ActivityLogViewHolder>() {

    private var mActivityLogList: List<ActivityLog>? = null

    fun updateActivityLogList(activityLogList: List<ActivityLog>) {
        mActivityLogList = activityLogList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityLogViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.layout_item_activity, parent, false)
        return ActivityLogViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityLogViewHolder, position: Int) {
        holder.updateData(mActivityLogList!![position])
    }

    override fun getItemCount(): Int {
        return mActivityLogList?.size ?: 0
    }


}