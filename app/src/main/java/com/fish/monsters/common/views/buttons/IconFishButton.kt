package com.fish.monsters.common.views.buttons

import androidx.compose.foundation.background
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
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fish.monsters.common.extensions.drawNeonStroke
import com.fish.monsters.common.extensions.isPreview
import com.fish.monsters.common.extensions.performVibration
import com.fish.monsters.common.extensions.previewGetSoundsManager
import com.fish.monsters.common.models.ui.IconProps
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.common.utils.SoundsManager
import com.fish.monsters.common.utils.settings.SettingsGlobalState
import com.fish.monsters.common.utils.settings.SettingsManager
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.FishMonstersTheme
import org.koin.compose.koinInject

@Composable
fun IconFishButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconProps: IconProps,
    settingsGlobalState: SettingsGlobalState = if (isPreview()) SettingsGlobalState() else koinInject<SettingsManager>().state.value,
) {
    val soundManager: SoundsManager = if (isPreview()) previewGetSoundsManager() else koinInject()
    val partiallyCutCornerShape = remember {
        PartiallyCutCornerShape(DpSize(11.dp, 22.dp))
    }
    val localView = LocalView.current
    Box(
        modifier = modifier
            .drawWithContent {
                drawContent()
                if (settingsGlobalState.neonStyles)
                    drawNeonStroke(DpSize(11.dp, 22.dp))
            }
            .size(48.dp)
            .clip(partiallyCutCornerShape)
            .background(DarkPrimaryColor, partiallyCutCornerShape)
            .clickable {
                soundManager.playDefaultButtonSound()
                localView.performVibration(settingsGlobalState.vibration)
                onClick()
            },
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
            Row(Modifier.padding(20.dp), horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                IconFishButton(
                    onClick = {},
                    iconProps = IconProps(icon = Icons.Default.QuestionMark)
                )
                IconFishButton(
                    onClick = {},
                    iconProps = IconProps(icon = Icons.Default.QuestionMark), settingsGlobalState = SettingsGlobalState(neonStyles = true)
                )
            }
        }
    }
}