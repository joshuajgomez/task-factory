package com.joshgm3z.taskfactory.view.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun DashboardContainer() {
    Surface {
        ConstraintLayout(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            val (textTitle, layoutTask, layoutLog, layoutWorker) = createRefs()
            Text(
                fontSize = 20.sp,
                text = "task-factory",
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .constrainAs(textTitle) {
                        top.linkTo(parent.top, margin = 10.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Surface(modifier = Modifier
                .fillMaxWidth(0.5f)
                .constrainAs(layoutTask) {
                    top.linkTo(textTitle.bottom, margin = 10.dp)
                    start.linkTo(parent.start, margin = 3.dp)
                }) {
            TaskContainer()
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.48f)
                    .fillMaxHeight(0.49f)
                    .constrainAs(layoutWorker) {
                        top.linkTo(layoutTask.top)
                        start.linkTo(layoutTask.end, margin = 3.dp)
                        end.linkTo(parent.end, margin = 3.dp)
                    }
            ) {
                WorkContainer()
            }
            Surface(
                modifier = Modifier
                    .fillMaxWidth(0.48f)
                    .fillMaxHeight(0.49f)
                    .constrainAs(layoutLog) {
                        top.linkTo(layoutWorker.bottom, margin = 3.dp)
                        start.linkTo(layoutWorker.start)
                        end.linkTo(layoutWorker.end)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                ActivityContainer()
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewDashboard() {
    DashboardContainer()
}