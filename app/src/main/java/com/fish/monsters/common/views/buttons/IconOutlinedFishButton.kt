package com.fish.monsters.common.views.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fish.monsters.common.extensions.drawNeonStroke
import com.fish.monsters.common.extensions.isPreview
import com.fish.monsters.common.models.ui.IconOutlinedProps
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.DarkPrimaryColorA12
import com.fish.monsters.core.theme.FishMonstersTheme
import com.fish.monsters.features.settings.data.SettingsManager
import org.koin.compose.koinInject

@Composable
fun IconOutlinedFishButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconProps: IconOutlinedProps,
    neonStyle: Boolean = if (isPreview()) false else koinInject<SettingsManager>().state.value.neonStyles
) {
    val partiallyCutCornerShape = remember {
        PartiallyCutCornerShape(DpSize(11.dp, 22.dp))
    }
    Box(
        modifier = modifier
            .drawWithContent {
                drawContent()
                if (neonStyle)
                    drawNeonStroke(DpSize(11.dp, 22.dp))
            }
            .size(48.dp)
            .clip(partiallyCutCornerShape)
            .border(
                1.dp,
                if (neonStyle) Color.Transparent else DarkPrimaryColor,
                partiallyCutCornerShape
            )
            .background(DarkPrimaryColorA12)
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
private fun IconOutlinedFishButtonPreview() {
    FishMonstersTheme {
        Surface {
            Row(Modifier.padding(20.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                IconOutlinedFishButton(
                    onClick = {},
                    iconProps = IconOutlinedProps(icon = Icons.Default.QuestionMark)
                )
                IconOutlinedFishButton(
                    onClick = {},
                    iconProps = IconOutlinedProps(icon = Icons.Default.QuestionMark),
                    neonStyle = true
                )
            }
        }
    }
}