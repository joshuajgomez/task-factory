package com.joshgm3z.taskfactory.view.worker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joshgm3z.taskfactory.R
import com.joshgm3z.taskfactory.common.utils.Logger
import com.joshgm3z.taskfactory.model.room.entity.Worker
import javax.inject.Inject

class WorkerAdapter @Inject constructor() : RecyclerView.Adapter<WorkerViewHolder>() {

    private var mWorkerList: ArrayList<Worker> = ArrayList()

    fun updateWorkerList(workerList: List<Worker>) {
        Logger.entryLog()
        mWorkerList.clear()
        mWorkerList.addAll(workerList)
        mWorkerList.sortByDescending { worker -> worker.status }
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return mWorkerList[position].status
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.layout_item_worker, parent, false)
        return WorkerViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {
        holder.updateData(mWorkerList[position])
    }

    override fun getItemCount(): Int {
        return mWorkerList.size
    }

}