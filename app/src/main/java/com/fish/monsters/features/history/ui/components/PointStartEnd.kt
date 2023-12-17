package com.fish.monsters.features.history.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.DarkPrimaryColorA12

@Composable
fun PointStartEnd(
    shape: PartiallyCutCornerShape = PartiallyCutCornerShape(
        DpSize(2.dp, 6.dp)
    ),
    checked: Boolean
) {
    Box(
        modifier = Modifier
            .height(9.dp)
            .width(10.dp)
            .border(BorderStroke(1.dp, DarkPrimaryColor), shape = shape)
            .background(
                if (checked) DarkPrimaryColor else DarkPrimaryColorA12,
                shape
            )
    )
}

@Preview
@Composable
fun PointStartEndPreview() {
    PointStartEnd(checked = false)
}
