package com.joshgm3z.taskfactory.view.container.worker

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.joshgm3z.taskfactory.utils.RandomData
import com.joshgm3z.taskfactory.model.room.entity.Worker
import com.joshgm3z.taskfactory.view.common.Gray4
import com.joshgm3z.taskfactory.view.common.Material3AppTheme
import com.joshgm3z.taskfactory.view.common.*

@Composable
fun WorkContainer(
    workerList: List<Worker>,
    onWorkersClearClick: () -> Unit,
    onAddWorkerClick: () -> Unit,
) {
    Log.w("Josh", "Re-compose: WorkContainer")
    Card(
        shape = RoundedCornerShape(containerCardBorderRadius)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxSize(1f)
                .padding(start = 1.dp)
        ) {
            val (textTitle, list, addButton, deleteIcon) = createRefs()
            Text(
                text = "Workers(${workerList.size})",
                modifier = Modifier
                    .constrainAs(textTitle) {
                        start.linkTo(parent.start, margin = titleMarginStart)
                        top.linkTo(parent.top, margin = titleMarginTop)
                    },
                fontSize = titleFontSize,
                color = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "+Recruit",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = buttonFontSize,
                modifier = Modifier
                    .constrainAs(addButton) {
                        end.linkTo(deleteIcon.start, margin = 2.dp)
                        top.linkTo(textTitle.top)
                        bottom.linkTo(textTitle.bottom)
                    }
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = CircleShape
                    )
                    .padding(start = 6.dp, end = 6.dp, top = 1.dp, bottom = 2.dp)
                    .clickable { onAddWorkerClick() }
            )
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Localized description",
                modifier = Modifier
                    .size(deleteIconSize)
                    .constrainAs(deleteIcon) {
                        end.linkTo(parent.end, margin = deleteIconMarginEnd)
                        top.linkTo(textTitle.top)
                        bottom.linkTo(textTitle.bottom)
                    }
                    .clickable { onWorkersClearClick() })
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .constrainAs(list) {
                        top.linkTo(textTitle.bottom, margin = listMarginTop)
                        start.linkTo(parent.start)
                    }) {
                WorkerList(workerList = workerList)
            }
        }
    }
}

@Composable
fun WorkerList(workerList: List<Worker>) {
    AnimatedVisibility(
        visible = workerList.isEmpty(),
        modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 30.dp)
    ) {
        Text(
            text = "No workers yet. Recruit one by clicking +Recruit",
            textAlign = TextAlign.Center,
            fontSize = 13.sp,
            lineHeight = 16.sp,
            color = Gray4
        )
    }
    LazyColumn(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        items(items = workerList.sortedBy { it.status }) {
            WorkerItem(it)
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Preview(uiMode = UI_MODE_NIGHT_NO, showBackground = true, showSystemUi = true)
@Composable
fun PreviewWorkContainer() {
    Material3AppTheme() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(all = 20.dp)
        ) {
            Surface(modifier = Modifier.fillMaxWidth(0.55f)) {
                WorkContainer(RandomData.getWorkerList(), {}, {})
            }
            Surface(modifier = Modifier.fillMaxWidth(0.5f)) {}
        }
    }
}

