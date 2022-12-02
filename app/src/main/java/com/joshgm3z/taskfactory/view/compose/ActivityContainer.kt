package com.joshgm3z.taskfactory.view.compose

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.joshgm3z.taskfactory.R
import com.joshgm3z.taskfactory.common.utils.DateUtil
import com.joshgm3z.taskfactory.model.room.entity.ActivityLog
import com.joshgm3z.taskfactory.model.room.entity.Task

@Composable
fun ActivityContainer(
    logList: List<ActivityLog>,
    onClearLogClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(Dimens.containerCardBorderRadius)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxSize(1f)
        ) {
            val (textTitle, deleteIcon, list) = createRefs()
            Text(
                text = "Activity Log",
                modifier = Modifier
                    .constrainAs(textTitle) {
                        top.linkTo(parent.top, margin = Dimens.titleMarginTop)
                        start.linkTo(parent.start, margin = Dimens.titleMarginStart)
                    },
                fontSize = Dimens.titleFontSize,
                color = MaterialTheme.colorScheme.onSecondary
            )

            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Localized description",
                modifier = Modifier
                    .size(Dimens.deleteIconSize)
                    .constrainAs(deleteIcon) {
                        top.linkTo(textTitle.top)
                        bottom.linkTo(textTitle.bottom)
                        end.linkTo(parent.end, margin = Dimens.deleteIconMarginEnd)
                    }
                    .clickable { onClearLogClick() })
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .constrainAs(list) {
                        top.linkTo(textTitle.bottom, margin = Dimens.listMarginTop)
                        start.linkTo(parent.start)
                    }) {
                LogList(logList = logList)
            }
        }
    }
}

@Composable
fun LogList(logList: List<ActivityLog>) {
    AnimatedVisibility(
        visible = logList.isEmpty(),
        modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 30.dp)
    ) {
        Text(
            text = "No activity yet. Add tasks and workers to get the party started!",
            textAlign = TextAlign.Center,
            fontSize = 13.sp,
            lineHeight = 16.sp
        )
    }
    LazyColumn {
        items(items = logList) {
            ActivityItem(it)
        }
    }
}

@Composable
fun ActivityItem(log: ActivityLog) {
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

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
fun PreviewActivity() {
    Material3AppTheme() {
        ActivityContainer(listOf(), {})
    }
}