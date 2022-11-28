package com.joshgm3z.taskfactory.view.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun DashboardContainer() {
    ConstraintLayout(modifier = Modifier.background(Color.Black)) {
        val (layoutTask, layoutLog, layoutWorker) = createRefs()
        Card(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .constrainAs(layoutTask) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
        ) {
            TaskContainer()
        }
        Card(
            modifier = Modifier
                .fillMaxWidth(0.49f)
                .fillMaxHeight(0.5f)
                .constrainAs(layoutWorker) {
                    top.linkTo(layoutTask.top)
                    start.linkTo(layoutTask.end, margin = 3.dp)
                }
        ) {
            TaskContainer()
        }
        Card(
            modifier = Modifier
                .fillMaxWidth(0.49f)
                .fillMaxHeight(0.5f)
                .constrainAs(layoutLog) {
                    top.linkTo(layoutWorker.bottom, margin = 3.dp)
                    start.linkTo(layoutTask.end, margin = 3.dp)
                }
        ) {
            ActivityContainer()
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewDashboard() {
    DashboardContainer()
}