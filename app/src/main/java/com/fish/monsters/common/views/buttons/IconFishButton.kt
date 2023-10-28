package com.fish.monsters.common.views.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fish.monsters.common.models.ui.IconProps
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.FishMonstersTheme

@Composable
fun IconFishButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconProps: IconProps
) {
    val partiallyCutCornerShape = remember {
        PartiallyCutCornerShape(DpSize(11.dp, 22.dp))
    }
    Box(
        modifier = modifier
            .size(48.dp)
            .clip(partiallyCutCornerShape)
            .background(DarkPrimaryColor, partiallyCutCornerShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        with(iconProps) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = tint,
                modifier = iconModifier
            )
        }
    }
}

@Preview
@Composable
private fun IconFishButtonPreview() {
    FishMonstersTheme {
        Surface {
            IconFishButton(
                onClick = {},
                iconProps = IconProps(icon = Icons.Default.QuestionMark)
            )
        }
    }
}