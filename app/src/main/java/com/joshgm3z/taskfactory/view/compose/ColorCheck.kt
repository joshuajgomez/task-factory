package com.joshgm3z.taskfactory.view.compose

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ColorCheck() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background)
    ) {
        MessageCard("op p", MaterialTheme.colorScheme.onPrimary, MaterialTheme.colorScheme.primary)
        MessageCard(
            "os s",
            MaterialTheme.colorScheme.onSecondary,
            MaterialTheme.colorScheme.secondary
        )
        MessageCard(
            "ot t",
            MaterialTheme.colorScheme.onTertiary,
            MaterialTheme.colorScheme.tertiary
        )
        MessageCard("oe e", MaterialTheme.colorScheme.onError, MaterialTheme.colorScheme.error)
        MessageCard(
            "opc pc",
            MaterialTheme.colorScheme.onPrimaryContainer,
            MaterialTheme.colorScheme.primaryContainer
        )
        MessageCard(
            "osc sc",
            MaterialTheme.colorScheme.onSecondaryContainer,
            MaterialTheme.colorScheme.secondaryContainer
        )
        MessageCard(
            "otc tc",
            MaterialTheme.colorScheme.onTertiaryContainer,
            MaterialTheme.colorScheme.tertiaryContainer
        )
        MessageCard(
            "ob b",
            MaterialTheme.colorScheme.onBackground,
            MaterialTheme.colorScheme.background
        )
        MessageCard("os s", MaterialTheme.colorScheme.onSurface, MaterialTheme.colorScheme.surface)
    }

}

@Composable
fun MessageCard(message: String, item: Color, background: Color) {
    Text(
        text = message,
        color = item,
        modifier = Modifier
            .background(
                color = background,
                shape = CircleShape
            )
            .padding(start = 10.dp, end = 10.dp, top = 3.dp, bottom = 4.dp)
    )
}

//@Preview()
//@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_NO)
@Preview(showBackground = true, showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewColorCheck() {
    ColorCheck()
}