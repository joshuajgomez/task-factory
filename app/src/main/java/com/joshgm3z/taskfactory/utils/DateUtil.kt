package com.joshgm3z.taskfactory.utils

import java.text.SimpleDateFormat

class DateUtil {

    companion object {
        fun getPrettyDate(instance: Long): String =
            SimpleDateFormat("MMM dd, h:mm aa").format(instance)
    }
}