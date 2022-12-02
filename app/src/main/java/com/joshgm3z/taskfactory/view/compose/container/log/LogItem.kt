package com.joshgm3z.taskfactory.view.compose.container.log

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joshgm3z.taskfactory.common.utils.DateUtil
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog

@Composable
fun LogItem(log: ActivityLog) {
    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = DateUtil.getPrettyDate(log.dateFinished),
            fontSize = 9.sp,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
        Text(
            text = log.description,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            lineHeight = 15.sp

        )
    }
}
