package com.joshgm3z.taskfactory.view.worker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joshgm3z.taskfactory.R
import com.joshgm3z.taskfactory.common.utils.Logger
import com.joshgm3z.taskfactory.model.room.entity.Worker

class WorkerAdapter : RecyclerView.Adapter<WorkerViewHolder>() {

    private var mWorkerList: ArrayList<Worker> = ArrayList()

    fun updateWorkerList(workerList: List<Worker>) {
        Logger.entryLog()
        if (workerList.isEmpty()) {
            mWorkerList.clear()
            notifyDataSetChanged()
        } else if (mWorkerList.isEmpty()) {
            mWorkerList.addAll(workerList)
            notifyItemRangeInserted(0, mWorkerList.size)
        } else if (mWorkerList.size != workerList.size) {
            var indexOf = mWorkerList.indexOfFirst { it.status == Worker.STATUS_BUSY }
            if (indexOf != -1)
                indexOf++
            else
                indexOf = mWorkerList.size
            mWorkerList.add(indexOf, workerList.last())
            notifyItemInserted(indexOf)
        } else {
            // ignore update. no change in list
        }
    }

    fun notifyStatusChange(worker: Worker) {
        Logger.log("worker = [${worker}]")
        var indexOf = mWorkerList.indexOfFirst { it.id == worker.id }
        mWorkerList.removeAt(indexOf)
        var toPosition =
            if (worker.status == Worker.STATUS_BUSY) 0
            else mWorkerList.indexOfFirst { it.status == Worker.STATUS_IDLE }
        if (toPosition == -1) toPosition = mWorkerList.size
        mWorkerList.add(toPosition, worker)
        notifyItemMoved(indexOf, toPosition)
        notifyItemChanged(indexOf)
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