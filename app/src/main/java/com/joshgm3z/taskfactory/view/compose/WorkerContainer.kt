package com.joshgm3z.taskfactory.view.compose

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.HourglassEmpty
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.joshgm3z.taskfactory.R

@Composable
fun WorkerItem(index: Int) {
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
            Text(text = "Worker #$index",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier
                    .constrainAs(textName) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start, margin = 10.dp)
                    }
            )
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onTertiary,
                modifier = Modifier
                    .width(18.dp)
                    .height(18.dp)
                    .constrainAs(pbLoading) {
                        top.linkTo(textName.top)
                        bottom.linkTo(textName.bottom)
                        end.linkTo(iconClock.start)
                    }
            )
            Icon(
                imageVector = Icons.Outlined.HourglassEmpty,
                contentDescription = "finished",
                modifier = Modifier
                    .size(18.dp)
                    .constrainAs(iconClock) {
                        top.linkTo(textName.top)
                        bottom.linkTo(textName.bottom)
                        end.linkTo(textCount.start, margin = 10.dp)
                    })
            Text(
                text = "5",
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
fun WorkContainer() {
    val workerCount = 10
    Card(
        shape = RoundedCornerShape(Dimens.containerCardBorderRadius)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .fillMaxSize(1f)
                .padding(start = 5.dp)
        ) {
            val (textTitle, list, count, addButton, deleteIcon) = createRefs()
            Text(
                text = "Workers($workerCount)",
                modifier = Modifier
                    .constrainAs(textTitle) {
                        start.linkTo(parent.start, margin = Dimens.titleMarginStart)
                        top.linkTo(parent.top, margin = Dimens.titleMarginTop)
                    },
                fontSize = Dimens.titleFontSize,
                color = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "+Recruit",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontSize = Dimens.buttonFontSize,
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
            )
            Icon(
                imageVector = Icons.Outlined.Delete,
                contentDescription = "Localized description",
                modifier = Modifier
                    .size(Dimens.deleteIconSize)
                    .constrainAs(deleteIcon) {
                        end.linkTo(parent.end, margin = Dimens.deleteIconMarginEnd)
                        top.linkTo(textTitle.top)
                        bottom.linkTo(textTitle.bottom)
                    })
            LazyColumn(modifier = Modifier
                .constrainAs(list) {
                    top.linkTo(textTitle.bottom, margin = Dimens.listMarginTop)
                    start.linkTo(parent.start)
                }
            ) {
                items(count = workerCount) {
                    WorkerItem(it)
                }
            }
        }
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES, showBackground = true, showSystemUi = true)
@Preview(uiMode = UI_MODE_NIGHT_NO)
@Composable
fun PreviewWorkContainer() {
    Material3AppTheme() {
        WorkContainer()
    }
}

