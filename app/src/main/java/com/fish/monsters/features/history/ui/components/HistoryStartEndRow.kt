package com.fish.monsters.features.history.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.core.theme.DarkPrimaryColor

@Composable
fun HistoryStartEndRow(title: String, isEndRow: Boolean, spacerHeight: Dp = 0.dp) {
    Column {
        if (isEndRow) {
            Spacer(
                modifier = Modifier
                    .padding(end = 36.dp)
                    .height(spacerHeight)
                    .width(1.dp)
                    .background(DarkPrimaryColor)
                    .align(Alignment.End)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(end = 30.dp)
                .fillMaxWidth()
                .height(30.dp)
        ) {
            Text(
                text = title,
                fontSize = 22.sp
            )
            Spacer(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .weight(1f)
                    .height(1.dp)
                    .background(DarkPrimaryColor)
            )
            EnhancementPoint(checked = true)
        }
    }
}

@Preview
@Composable
private fun HistoryStartEndRowPreview() {
    PreviewContainer {
        HistoryStartEndRow(title = "Start gry", isEndRow = true, spacerHeight = 40.dp)
    }
}
