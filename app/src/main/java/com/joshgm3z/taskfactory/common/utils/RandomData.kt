package com.joshgm3z.taskfactory.common.utils

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
                "Joshua",
                "Sani",
                "Savu",
                "Ruth",
                "Tanya",
                "Sandeep",
                "Albin",
                "Pinky",
                "Sachin",
                "Mohanlal",
            )
            return list.random()
        }
    }

}