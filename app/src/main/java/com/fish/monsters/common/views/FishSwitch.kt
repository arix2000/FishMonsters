package com.fish.monsters.common.views

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateSizeAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fish.monsters.common.extensions.animateAlignmentAsState
import com.fish.monsters.common.extensions.isPreview
import com.fish.monsters.common.extensions.performVibration
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.common.utils.settings.SettingsManager
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.DarkPrimaryColorA12
import com.fish.monsters.core.theme.FishMonstersTheme
import com.fish.monsters.core.theme.TextColorDark
import org.koin.compose.koinInject

@Composable
fun FishSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    shouldVibrate: Boolean = if (isPreview()) true else koinInject<SettingsManager>().state.value.vibration
) {
    val colorAnimations = tween<Color>(300, easing = LinearEasing)

    var alignment by remember {
        mutableStateOf(
            if (checked) Alignment.CenterEnd else Alignment.CenterStart
        )
    }
    val animatedAlignment by animateAlignmentAsState(alignment)

    var thumbSize by remember {
        mutableStateOf(if (!checked) 16.dp else 20.dp)
    }
    val animatedThumbSize by animateSizeAsState(
        targetValue = Size(thumbSize.value, thumbSize.value),
        label = "animateSizeAsState",
    )

    var containerColor by remember {
        mutableStateOf(if (checked) DarkPrimaryColor else DarkPrimaryColorA12)
    }
    val animatedContainerColor by animateColorAsState(
        targetValue = containerColor, label = "animateColorAsState", animationSpec = colorAnimations

    )

    var thumbColor by remember {
        mutableStateOf(if (checked) TextColorDark else DarkPrimaryColor)
    }
    val animatedThumbColor by animateColorAsState(
        targetValue = thumbColor, label = "animateColorAsState", animationSpec = colorAnimations
    )

    val containerShape = PartiallyCutCornerShape(DpSize(6.dp, 21.dp))
    val thumbShape = PartiallyCutCornerShape(DpSize(3.dp, 10.dp))

    val localView = LocalView.current
    Box(modifier = modifier
        .clip(containerShape)
        .clickable(
            indication = null,
            interactionSource = remember { MutableInteractionSource() },
        ) {
            localView.performVibration(shouldVibrate)
            thumbColor = if (checked) DarkPrimaryColor else TextColorDark
            containerColor = if (checked) DarkPrimaryColorA12 else DarkPrimaryColor
            thumbSize = if (checked) 16.dp else 20.dp
            alignment = if (checked) Alignment.CenterStart else Alignment.CenterEnd
            onCheckedChange(!checked)
        }
        .background(
            animatedContainerColor, containerShape
        )
        .height(35.dp)
        .width(60.dp)
        .border(1.dp, DarkPrimaryColor, containerShape)
        .padding(8.dp)) {
        Box(
            modifier = Modifier
                .background(animatedThumbColor, thumbShape)
                .size(animatedThumbSize.width.dp, animatedThumbSize.height.dp)
                .align(animatedAlignment)
        )
    }
}

@Preview
@Composable
private fun FishSwitchPreview() {
    FishMonstersTheme {
        Surface {
            Row(modifier = Modifier.padding(20.dp)) {
                FishSwitch(checked = false, onCheckedChange = {})
                Spacer(modifier = Modifier.width(20.dp))
                FishSwitch(checked = true, onCheckedChange = {})
            }
        }
    }
}