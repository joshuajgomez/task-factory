package com.joshgm3z.taskfactory.view.compose.container.worker

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.HourglassEmpty
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.joshgm3z.taskfactory.common.utils.RandomData
import com.joshgm3z.taskfactory.model.room.entity.Worker
import com.joshgm3z.taskfactory.view.compose.common.Material3AppTheme

@Composable
fun WorkerItem(worker: Worker) {
    Card(
        shape = RoundedCornerShape(2.dp),
        modifier = Modifier
            .padding(bottom = 3.dp, start = 3.dp, end = 3.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth(1f)
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(top = 5.dp, bottom = 5.dp)
        ) {
            val (textName, pbLoading, textCount, iconClock) = createRefs()
            Text(text = worker.name,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier
                    .constrainAs(textName) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start, margin = 10.dp)
                    }
            )
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .width(18.dp)
                    .height(18.dp)
                    .constrainAs(pbLoading) {
                        top.linkTo(textName.top)
                        bottom.linkTo(textName.bottom)
                        end.linkTo(textCount.start, margin = 10.dp)
                    }) {
                if (worker.status == Worker.STATUS_BUSY) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onTertiary,
                        modifier = Modifier
                            .width(18.dp)
                            .height(18.dp)

                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.HourglassEmpty,
                        contentDescription = "finished",
                        modifier = Modifier.size(18.dp))
                }
            }
            Text(
                text = "${worker.jobCount}",
                fontSize = 13.sp,
                color = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .constrainAs(textCount) {
                        top.linkTo(textName.top)
                        bottom.linkTo(textName.bottom)
                        end.linkTo(parent.end, margin = 10.dp)
                    }
                    .background(
                        color = MaterialTheme.colorScheme.tertiaryContainer,
                        shape = CircleShape
                    )
                    .padding(start = 5.dp, end = 5.dp)
            )

        }
    }
}

@Composable
@Preview
fun PreviewWorkerItem() {
    val workerIdle = Worker("Idle worker")
    workerIdle.status = Worker.STATUS_IDLE

    val workerBusy = Worker("Busy worker")
    workerBusy.status = Worker.STATUS_BUSY

    Material3AppTheme {
        Column {
            WorkerItem(worker = workerIdle)
            WorkerItem(worker = workerBusy)
        }
    }
}