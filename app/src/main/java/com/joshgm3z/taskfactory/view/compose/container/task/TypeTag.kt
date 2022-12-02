package com.joshgm3z.taskfactory.view.compose.container.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.view.compose.common.*

@Composable
fun TypeTag(type: Int) {
    val backgroundColor: Color = when (type) {
        Task.TYPE_COOKING -> Yellow1
        Task.TYPE_CLEANING -> Green1
        Task.TYPE_HELPING -> Blue1
        Task.TYPE_MAINTENANCE -> Red1
        Task.TYPE_BUSINESS -> Purple1
        Task.TYPE_ERRAND -> Orange1
        else -> Yellow1
    }
    Text(
        text = Task.getTypeText(type),
        fontSize = 10.sp,
        color = Color.Black,
        modifier = Modifier
            .background(backgroundColor, RoundedCornerShape(4.dp))
            .padding(start = 3.dp, end = 3.dp)
    )
}

@Preview
@Composable
fun PreviewTypeTag() {
    Material3AppTheme() {
        Column {
            TypeTag(type = Task.TYPE_BUSINESS)
            TypeTag(type = Task.TYPE_CLEANING)
            TypeTag(type = Task.TYPE_ERRAND)
            TypeTag(type = Task.TYPE_HELPING)
            TypeTag(type = Task.TYPE_COOKING)
            TypeTag(type = Task.TYPE_MAINTENANCE)
        }
    }
}