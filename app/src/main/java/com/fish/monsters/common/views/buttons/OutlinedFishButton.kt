package com.fish.monsters.common.views.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fish.monsters.common.extensions.drawNeonStroke
import com.fish.monsters.common.extensions.isPreview
import com.fish.monsters.common.extensions.performVibration
import com.fish.monsters.common.extensions.previewGetSoundsManager
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.common.utils.SoundsManager
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.DarkPrimaryColorA12
import com.fish.monsters.core.theme.FishMonstersTheme
import com.fish.monsters.common.utils.settings.SettingsManager
import com.fish.monsters.common.utils.settings.SettingsGlobalState
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedFishButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    indentationSize: DpSize = DpSize(12.dp, 24.dp),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = DarkPrimaryColorA12,
    ),
    settingsGlobalState: SettingsGlobalState = if (isPreview()) SettingsGlobalState() else koinInject<SettingsManager>().state.value,
    soundManager: SoundsManager = if (isPreview()) previewGetSoundsManager() else koinInject(),
    content: @Composable RowScope.() -> Unit
) {
    val localView = LocalView.current
    CompositionLocalProvider(
        LocalMinimumTouchTargetEnforcement provides false,
    ) {
        if (settingsGlobalState.neonStyles)
            OutlinedFishButtonNeonStyle(
                onClick = {
                    soundManager.playDefaultButtonSound()
                    localView.performVibration(settingsGlobalState.vibration)
                    onClick()
                },
                modifier = modifier,
                shape = PartiallyCutCornerShape(indentationSize),
                indentationSize = indentationSize,
                contentPadding = contentPadding,
                interactionSource = interactionSource,
                enabled = enabled,
                colors = colors,
                content = content,
            )
        else
            Button(
                onClick = {
                    soundManager.playDefaultButtonSound()
                    localView.performVibration(settingsGlobalState.vibration)
                    onClick()
                },
                modifier = modifier,
                shape = PartiallyCutCornerShape(indentationSize),
                border = border ?: BorderStroke(1.dp, DarkPrimaryColor),
                contentPadding = contentPadding,
                interactionSource = interactionSource,
                enabled = enabled,
                colors = colors,
                content = content,
            )
    }
}

@Composable
private fun OutlinedFishButtonNeonStyle(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    shape: Shape,
    indentationSize: DpSize,
    colors: ButtonColors,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.drawWithContent {
            drawContent()
            drawNeonStroke(indentationSize)
        },
        shape = shape,
        border = BorderStroke(1.dp, Color.Transparent),
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        enabled = enabled,
        colors = colors,
        content = content,
    )
}

@Preview
@Composable
private fun OutlinedFishButtonPreview() {
    FishMonstersTheme {
        Box(modifier = Modifier.padding(20.dp)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                OutlinedFishButton(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Default indentationSize")
                }
                OutlinedFishButton(
                    onClick = {},
                    indentationSize = DpSize(10.dp, 10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "indentationSize = DpSize(10.dp, 10.dp)")
                }
                OutlinedFishButton(
                    onClick = {},
                    indentationSize = DpSize(12.dp, 30.dp),
                    contentPadding = PaddingValues(13.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "indentationSize = DpSize(12.dp, 30.dp)")
                }
                Text(text = "neonStyle true:")
                OutlinedFishButton(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    settingsGlobalState = SettingsGlobalState(neonStyles = true)
                ) {
                    Text(text = "Default indentationSize")
                }
                OutlinedFishButton(
                    onClick = {},
                    indentationSize = DpSize(10.dp, 10.dp),
                    modifier = Modifier.fillMaxWidth(),
                    settingsGlobalState = SettingsGlobalState(neonStyles = true)
                ) {
                    Text(text = "indentationSize = DpSize(10.dp, 10.dp)")
                }
                OutlinedFishButton(
                    onClick = {},
                    indentationSize = DpSize(12.dp, 30.dp),
                    contentPadding = PaddingValues(13.dp),
                    modifier = Modifier.fillMaxWidth(),
                    settingsGlobalState = SettingsGlobalState(neonStyles = true)
                ) {
                    Text(text = "indentationSize = DpSize(12.dp, 30.dp)")
                }
            }

        }
    }
}