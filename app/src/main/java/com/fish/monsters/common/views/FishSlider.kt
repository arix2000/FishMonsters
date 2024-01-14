package com.fish.monsters.common.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.FishMonstersTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FishSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    onValueChangeFinished: (() -> Unit)? = null,
) {
    Slider(
        value = value,
        onValueChange = onValueChange,
        onValueChangeFinished = onValueChangeFinished,
        track = { sliderPosition ->
            val surfaceVariant = MaterialTheme.colorScheme.surfaceVariant
            Canvas(modifier = Modifier
                .height(8.dp)
                .fillMaxWidth(),
                onDraw = {
                    clipPath(
                        PartiallyCutCornerShape.drawPath(
                            size,
                            Density(density, fontScale),
                            DpSize(12.dp, 4.dp)
                        )
                    ) {
                        drawRect(surfaceVariant)
                        drawRect(
                            DarkPrimaryColor,
                            size = Size(size.width * sliderPosition.activeRange.endInclusive , size.height)
                        )
                    }
                })
        },
        thumb = {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .shadow(10.dp, PartiallyCutCornerShape(DpSize(3.dp, 12.dp)))
                    .background(
                        DarkPrimaryColor,
                        shape = PartiallyCutCornerShape(DpSize(3.dp, 12.dp))
                    )
            )
        })
}

@Preview
@Composable
private fun FishSliderPreview() {
    FishMonstersTheme {
        Surface {
            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                modifier = Modifier.padding(20.dp)
            ) {
                FishSlider(0f, {})
                FishSlider(0.44f, {})
                FishSlider(1f, {})
            }
        }
    }
}