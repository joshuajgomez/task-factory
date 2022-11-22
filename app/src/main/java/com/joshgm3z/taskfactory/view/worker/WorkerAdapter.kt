package com.joshgm3z.taskfactory.view.worker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joshgm3z.taskfactory.R
import com.joshgm3z.taskfactory.common.utils.Logger
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker
import javax.inject.Inject

class WorkerAdapter @Inject constructor() : RecyclerView.Adapter<WorkerViewHolder>() {

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
            mWorkerList.add(workerList.last())
            notifyItemInserted(mWorkerList.size - 1)
        } else {
            // ignore update. no change in list
        }
//        mWorkerList.sortByDescending { worker -> worker.status }
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

    fun notifyStatusChange(worker: Worker) {
        var indexOf = -1
        mWorkerList.forEachIndexed { index, _worker -> if (worker.id == _worker.id) indexOf = index }
        Logger.log(
            "worker.id = [${worker.id}], worker.name = [${worker.name}], " +
                    "worker.status = [${worker.getStatusText()}], indexOf = [${indexOf}]"
        )
        mWorkerList[indexOf] = worker
        notifyItemChanged(indexOf)
    }

}