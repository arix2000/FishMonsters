package com.fish.monsters.common.shapes

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection

class PartiallyCutCornerShape(private val indentationSize: DpSize) : Shape {
    override fun createOutline(
        size: Size, layoutDirection: LayoutDirection, density: Density
    ): Outline {
        return Outline.Generic(
            drawPath(size, density, indentationSize)
        )
    }

    private fun drawPath(size: Size, density: Density, indentationSize: DpSize): Path {
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