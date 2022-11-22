package com.joshgm3z.taskfactory.model.engine

import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker

data class ActiveTask(val task: Task, val worker: Worker, var timeLeft: Int) {

    override fun toString(): String {
        return "ActiveTask(" +
                "task.id=${task.id}, " +
                "task.description=${task.description}, " +
                "worker.id=${worker.id}, " +
                "worker.name=${worker.name}, " +
                "worker.status=${worker.getStatusText()}, " +
                "timeLeft=$timeLeft) "
    }
}
