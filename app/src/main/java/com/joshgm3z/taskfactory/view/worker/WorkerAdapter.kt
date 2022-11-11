package com.joshgm3z.taskfactory.view.worker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joshgm3z.taskfactory.R
import com.joshgm3z.taskfactory.model.room.entity.Worker

class WorkerAdapter : RecyclerView.Adapter<WorkerViewHolder>() {

    private var mWorkerList: List<Worker>? = null

    fun updateWorkerList(workerList: List<Worker>) {
        mWorkerList = workerList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.layout_item_worker, parent, false)
        return WorkerViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {
        holder.updateData(mWorkerList!![position])
    }

    override fun getItemCount(): Int {
        return mWorkerList?.size ?: 0
    }


}