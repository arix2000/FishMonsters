package com.fish.monsters.common.extensions

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.core.theme.DarkPrimaryColor

fun ContentDrawScope.drawNeonStroke(indentationSize: DpSize, color: Color = DarkPrimaryColor) {
    this.drawIntoCanvas {
        val paint =
            Paint().apply {
                style = PaintingStyle.Stroke
                strokeWidth = 20f
            }

        val frameworkPaint =
            paint.asFrameworkPaint()

        this.drawIntoCanvas {
            frameworkPaint.color = color.copy(alpha = 0f).toArgb()
            frameworkPaint.setShadowLayer(
                20f, 0f, 0f, color.copy(alpha = .5f).toArgb()
            )
            it.drawPath(
                PartiallyCutCornerShape.drawPath(
                    size,
                    Density(density),
                    indentationSize
                ),
                paint = paint
            )
            drawPath(
                PartiallyCutCornerShape.drawPath(
                    size,
                    Density(density),
                    indentationSize
                ),
                color = color,
                style = Stroke(width = 1.dp.toPx())
            )
        }
    }
}