package com.fish.monsters.common.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.DarkPrimaryColorA12
import com.fish.monsters.core.theme.TextColorDark

@Composable
fun FishCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = PartiallyCutCornerShape(DpSize(6.dp, 14.dp))
    var containerColor by remember {
        mutableStateOf(if (checked) DarkPrimaryColor else DarkPrimaryColorA12)
    }
    val animatedContainerColor by animateColorAsState(
        targetValue = containerColor,
        label = "animateColorAsState",
        animationSpec = tween(300, easing = LinearEasing)
    )
    DisposableEffect(key1 = checked) {
        containerColor = if (checked) DarkPrimaryColor else Color.Transparent
        onDispose { }
    }

    Box(contentAlignment = Alignment.Center,
        modifier = modifier
            .size(28.dp)
            .clip(shape)
            .border(1.dp, DarkPrimaryColor, shape)
            .background(animatedContainerColor, shape)
            .clickable { onCheckedChange(!checked) }
    ) {
        AnimatedVisibility(visible = checked) {
            Icon(imageVector = Icons.Default.Check, contentDescription = "checked icon", tint = TextColorDark)
        }
    }
}

@Preview
@Composable
private fun FishCheckboxPreview() {
    PreviewContainer {
        Row {
            FishCheckbox(checked = true, onCheckedChange = {})
            Spacer(modifier = Modifier.width(10.dp))
            FishCheckbox(checked = false, onCheckedChange = {})
        }
    }
}