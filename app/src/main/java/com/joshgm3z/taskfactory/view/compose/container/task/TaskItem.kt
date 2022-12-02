package com.joshgm3z.taskfactory.view.compose.container.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.joshgm3z.taskfactory.common.utils.DateUtil
import com.joshgm3z.taskfactory.common.utils.RandomData
import com.joshgm3z.taskfactory.model.room.entity.Task
import com.joshgm3z.taskfactory.view.compose.common.Material3AppTheme

@Composable
fun TaskItem(task: Task) {
    Card(
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(bottom = 3.dp, start = 3.dp, end = 3.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.tertiary)
                .fillMaxWidth()
                .padding(bottom = 3.dp, start = 2.dp, end = 2.dp)
        ) {
            val (textName, textTime, pbLoading, textDuration, iconClock, textTag) = createRefs()

            Text(text = task.description,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier
                    .constrainAs(textName) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start, margin = 5.dp)
                    }
            )
            Text(
                text = DateUtil.getPrettyDate(task.timeAdded),
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .constrainAs(textTime) {
                        top.linkTo(textName.bottom, margin = 4.dp)
                        start.linkTo(textName.start)
                    }
            )
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .constrainAs(textTag) {
                        top.linkTo(textTime.top)
                        bottom.linkTo(textTime.bottom)
                        start.linkTo(textTime.end, margin = 4.dp)
                    }) {
                TypeTag(type = task.type)
            }
//            CircularProgressIndicator(
//                modifier = Modifier
//                    .width(20.dp)
//                    .height(20.dp)
//                    .constrainAs(pbLoading) {
//                        top.linkTo(textName.top)
//                        bottom.linkTo(textName.bottom)
//                        end.linkTo(parent.end)
//                    }
//            )
            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "finished",
                modifier = Modifier
                    .size(15.dp)
                    .constrainAs(pbLoading) {
                        top.linkTo(textName.top)
                        bottom.linkTo(textName.bottom)
                        end.linkTo(parent.end)
                    })
            Text(
                text = "${task.duration}",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .constrainAs(textDuration) {
                        end.linkTo(parent.end)
                        bottom.linkTo(textTime.bottom)
                        top.linkTo(textTime.top)
                    }
            )
            Icon(
                imageVector = Icons.Outlined.Schedule,
                contentDescription = "duration",
                modifier = Modifier
                    .size(10.dp)
                    .constrainAs(iconClock) {
                        end.linkTo(textDuration.start, margin = 3.dp)
                        bottom.linkTo(textDuration.bottom)
                        top.linkTo(textDuration.top)
                    })
        }
    }
}

@Preview
@Composable
fun PreviewTaskItem() {
    Material3AppTheme() {
        TaskItem(task = RandomData.getTask())
    }
}