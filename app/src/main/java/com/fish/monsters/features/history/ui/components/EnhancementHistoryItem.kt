package com.fish.monsters.features.history.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.core.database.entities.contest.Duration
import com.fish.monsters.core.database.entities.contest.Enhancement
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.DarkPrimaryColorA12

@Composable
fun EnhancementHistoryItem(
    enhancement: Enhancement,
    shape: PartiallyCutCornerShape = PartiallyCutCornerShape(
        DpSize(8.dp, 22.dp)
    ),
    height: Dp = 40.dp
) {
    Column {
        Spacer(
            modifier = Modifier
                .padding(end = 36.dp)
                .height(height)
                .width(1.dp)
                .background(DarkPrimaryColor)
                .align(Alignment.End)
        )
        Box(
            Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(end = 32.dp, top = 6.dp)
                    .fillMaxWidth()
                    .height(37.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(41.dp)
                        .border(BorderStroke(1.dp, DarkPrimaryColor), shape = shape)
                        .background(DarkPrimaryColorA12, shape = shape)
                ) {
                    Icon(
                        painter = painterResource(id = enhancement.iconId),
                        contentDescription = "Enhancement Icon",
                        modifier = Modifier
                            .size(17.dp)
                            .align(Alignment.Center),
                        tint = DarkPrimaryColor
                    )
                }
                Text(
                    text = stringResource(id = enhancement.getName()),
                    modifier = Modifier.padding(start = 8.dp)
                )
                Spacer(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .weight(1f)
                        .height(1.dp)
                        .background(DarkPrimaryColor)
                )
                EnhancementPoint(checked = false)
            }
            Text(
                text = enhancement.time.toString(true),
                modifier = Modifier.align(Alignment.TopEnd),
                fontSize = 14.sp
            )
        }
    }
}

@Preview
@Composable
private fun EnhancementHistoryItemPreview() {
    PreviewContainer {
        EnhancementHistoryItem(
            Enhancement(
                name = "good_winds",
                time = Duration(1, 25, 21)
            )
        )
    }
}
