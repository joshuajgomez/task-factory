package com.joshgm3z.taskfactory.model.engine

data class ActiveTask(var taskId: Int, var workerId: Int, var timeLeft: Int, var taskName: String, var workerName: String){
    override fun toString(): String {
        return "ActiveTask(taskId=$taskId, workerId=$workerId, timeLeft=$timeLeft, taskName='$taskName', workerName='$workerName')"
    }
}
