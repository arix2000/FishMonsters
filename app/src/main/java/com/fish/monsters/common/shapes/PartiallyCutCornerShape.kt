package com.fish.monsters.common.shapes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.fish.monsters.common.views.CapText
import com.fish.monsters.common.views.buttons.FishButton
import com.fish.monsters.common.views.buttons.OutlinedFishButton
import com.fish.monsters.core.theme.FishMonstersTheme

class PartiallyCutCornerShape(private val indentationSize: DpSize) : Shape {
    override fun createOutline(
        size: Size, layoutDirection: LayoutDirection, density: Density
    ): Outline {
        return Outline.Generic(
            drawPath(size, density, indentationSize)
        )
    }

    companion object {
        fun drawPath(size: Size, density: Density, indentationSize: DpSize): Path {
            val indentationHeight = with(density) { indentationSize.height.toPx() }
            val indentationWidth = with(density) { indentationSize.width.toPx() }
            return Path().apply {
                reset()
                moveTo(indentationWidth, 0f)
                lineTo(size.width, 0f)
                lineTo(size.width, size.height - indentationHeight)
                lineTo(size.width - indentationWidth, size.height)
                lineTo(0f, size.height)
                lineTo(0f, indentationHeight)
                lineTo(indentationWidth, 0f)
            }
        }
    }

}

@Preview
@Composable
private fun PartiallyCutCornerShapePreview() {
    FishMonstersTheme {
        Surface {
            Column(modifier = Modifier.padding(20.dp)) {
                FishButton(onClick = { }) {
                    CapText(text = "Default fish button")
                }
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedFishButton(
                    onClick = { },
                    border = BorderStroke(1.dp, Color.Transparent)
                ) {
                    CapText(text = "Outlined fish button")
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}