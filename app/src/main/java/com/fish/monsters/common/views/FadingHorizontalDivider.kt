package com.fish.monsters.common.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fish.monsters.core.theme.DarkPrimaryColorA50
import com.fish.monsters.core.theme.FishMonstersTheme

@Composable
fun FadingHorizontalDivider(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(
                Brush.linearGradient(
                    listOf(
                        Color.Transparent,
                        DarkPrimaryColorA50,
                        Color.Transparent,
                    )
                )
            )
    )
}

@Preview
@Composable
private fun FadingHorizontalDividerPreview() {
    FishMonstersTheme {
        Surface {
            FadingHorizontalDivider(modifier = Modifier.padding(20.dp))
        }
    }
}