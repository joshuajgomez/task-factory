package com.joshgm3z.taskfactory.common.utils

import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.model.room.entity.Worker

class RandomData {

    companion object {

        fun getTaskDuration(): Int {
            val list: List<Int> = listOf(5, 8, 9, 1, 2, 4, 3, 6, 7)
            return list.random()
        }

        fun getTaskName(): String {
            val list: List<String> = listOf(
                "Make bed",
                "Do something",
                "Cook breakfast",
                "Wipe floor",
                "Cook lunch",
                "Pickup dry cleaners",
                "Supply guns",
                "Call grandma",
                "Cook lunch",
                "Cook Dinner",
            )
            return list.random()
        }

        fun getWorkerName(): String {
            val list: List<String> = listOf(
                "Blob",
                "Barbera",
                "Savu",
                "Ruth",
                "Tanya",
                "Saran",
                "Albin",
                "Pinky",
                "Sachin",
                "Mohanlal",
            )
            return list.random()
        }

        fun getTask(): Task {
            val list: List<Task> = listOf(
                Task("Make bed", System.currentTimeMillis(), getTaskDuration(), Task.TYPE_CLEANING),
                Task("Do something",
                    System.currentTimeMillis(),
                    getTaskDuration(),
                    Task.TYPE_HELPING),
                Task("Cook breakfast",
                    System.currentTimeMillis(),
                    getTaskDuration(),
                    Task.TYPE_COOKING),
                Task("Wipe floor",
                    System.currentTimeMillis(),
                    getTaskDuration(),
                    Task.TYPE_CLEANING),
                Task("Cook lunch",
                    System.currentTimeMillis(),
                    getTaskDuration(),
                    Task.TYPE_COOKING),
                Task("Pickup dry cleaners",
                    System.currentTimeMillis(),
                    getTaskDuration(),
                    Task.TYPE_ERRAND),
                Task("Supply guns",
                    System.currentTimeMillis(),
                    getTaskDuration(),
                    Task.TYPE_BUSINESS),
                Task("Call grandma",
                    System.currentTimeMillis(),
                    getTaskDuration(),
                    Task.TYPE_HELPING),
                Task("Cook lunch",
                    System.currentTimeMillis(),
                    getTaskDuration(),
                    Task.TYPE_COOKING),
                Task("Cook Dinner",
                    System.currentTimeMillis(),
                    getTaskDuration(),
                    Task.TYPE_COOKING),
                Task("Source grenades",
                    System.currentTimeMillis(),
                    getTaskDuration(),
                    Task.TYPE_BUSINESS),
            )
            return list.random()
        }

        fun getTaskList(): List<Task> {
            val list: ArrayList<Task> = arrayListOf()
            list.add(getTask())
            list.add(getTask())
            list.add(getTask())
            list.add(getTask())
            list.add(getTask())
            list.add(getTask())
            list.add(getTask())
            list.add(getTask())
            list.add(getTask())
            list.add(getTask())
            return list
        }

        fun getWorkerList(): List<Worker> {
            val list: ArrayList<Worker> = arrayListOf()
            list.add(Worker(getWorkerName()))
            list.add(Worker(getWorkerName()))
            list.add(Worker(getWorkerName()))
            list.add(Worker(getWorkerName()))
            list.add(Worker(getWorkerName()))
            list.add(Worker(getWorkerName()))
            list.add(Worker(getWorkerName()))
            list.add(Worker(getWorkerName()))
            list.add(Worker(getWorkerName()))
            list.add(Worker(getWorkerName()))
            list.add(Worker(getWorkerName()))
            list.add(Worker(getWorkerName()))
            return list
        }

        fun getActivityList(): List<ActivityLog> {
            val list: ArrayList<ActivityLog> = arrayListOf()
            list.add(ActivityLog("New worker added: ${getWorkerName()}"))
            list.add(ActivityLog("${getWorkerName()} completed task ${getTaskName()})"))
            list.add(ActivityLog("New worker added: ${getWorkerName()}"))
            list.add(ActivityLog("New task added: ${getTaskName()}"))
            list.add(ActivityLog("${getWorkerName()} started working on task ${getTaskName()})"))
            list.add(ActivityLog("New task added: ${getTaskName()}"))
            list.add(ActivityLog("${getWorkerName()} started working on task ${getTaskName()})"))
            list.add(ActivityLog("${getWorkerName()} started working on task ${getTaskName()})"))
            list.add(ActivityLog("New task added: ${getTaskName()}"))
            list.add(ActivityLog("${getWorkerName()} started working on task ${getTaskName()})"))
            list.add(ActivityLog("New task added: ${getTaskName()}"))
            list.add(ActivityLog("${getWorkerName()} completed task ${getTaskName()})"))
            list.add(ActivityLog("${getWorkerName()} completed task ${getTaskName()})"))
            list.add(ActivityLog("New task added: ${getTaskName()}"))
            list.add(ActivityLog("${getWorkerName()} completed task ${getTaskName()})"))
            list.add(ActivityLog("New worker added: ${getWorkerName()}"))
            list.add(ActivityLog("New worker added: ${getWorkerName()}"))
            return list
        }
    }

}