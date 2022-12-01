package com.joshgm3z.taskfactory.view.compose

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
fun ActivityContainer() {
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
                    })
            LazyColumn(
                modifier = Modifier
                    .constrainAs(list) {
                        top.linkTo(textTitle.bottom, margin = Dimens.listMarginTop)
                        start.linkTo(parent.start)
                    }
            ) {
                items(count = 10) {
                    ActivityItem(it)
                }
            }
        }
    }
}

@Composable
fun ActivityItem(index: Int) {
    Column(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = "Nov 5, 2:00 PM",
            fontSize = 9.sp,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
        Text(
            text = "Joshua started working on task on task #$index",
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
        ActivityContainer()
    }
}