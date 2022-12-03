package com.joshgm3z.taskfactory.view.compose.container.log

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joshgm3z.taskfactory.common.utils.DateUtil
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.view.compose.common.Material3AppTheme

@Composable
fun LogItem(log: ActivityLog) {
    Column(
        modifier = Modifier
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = DateUtil.getPrettyDate(log.dateFinished),
            fontSize = 9.sp,
            color = MaterialTheme.colorScheme.onTertiary
        )
        Text(
            text = log.description,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            lineHeight = 15.sp

        )
    }
}

@Preview(name = "Light mode", uiMode = UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark mode", uiMode = UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewLogItem() {
    val log = ActivityLog(
        description = "Someone started working on something",
        dateFinished = System.currentTimeMillis()
    )
    Material3AppTheme {
        Column {
            LogItem(log = log)
            LogItem(log = log)
        }
    }
}
