package com.fish.monsters.common.views.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fish.monsters.common.extensions.drawNeonStroke
import com.fish.monsters.common.extensions.isPreview
import com.fish.monsters.common.extensions.performVibration
import com.fish.monsters.common.extensions.previewGetSoundsManager
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.common.utils.SoundsManager
import com.fish.monsters.common.utils.settings.SettingsGlobalState
import com.fish.monsters.common.utils.settings.SettingsManager
import com.fish.monsters.common.views.CapText
import com.fish.monsters.core.theme.DangerColor
import com.fish.monsters.core.theme.DangerColorA12
import com.fish.monsters.core.theme.FishMonstersTheme
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DangerOutlinedFishButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    indentationSize: DpSize = DpSize(12.dp, 24.dp),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = DangerColorA12,
    ),
    settingsGlobalState: SettingsGlobalState = if (isPreview()) SettingsGlobalState() else koinInject<SettingsManager>().state.value,
    content: @Composable() (RowScope.() -> Unit)
) {
    val soundManager: SoundsManager = if (isPreview()) previewGetSoundsManager() else koinInject()

    val localView = LocalView.current
    CompositionLocalProvider(
        LocalMinimumTouchTargetEnforcement provides false,
    ) {
        if (settingsGlobalState.neonStyles)
            DangerOutlinedFishButtonNeonStyle(
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
            OutlinedFishButton(
                modifier = modifier,
                onClick = onClick,
                border = border ?: BorderStroke(1.dp, DangerColor),
                contentPadding = contentPadding,
                interactionSource = interactionSource,
                enabled = enabled,
                colors = colors
            ) {
                ProvideTextStyle(TextStyle(color = DangerColor)) {
                    content()
                }
            }
    }

}

@Composable
fun DangerOutlinedFishButtonNeonStyle(
    onClick: () -> Unit,
    modifier: Modifier,
    shape: PartiallyCutCornerShape,
    indentationSize: DpSize,
    contentPadding: PaddingValues,
    interactionSource: MutableInteractionSource,
    enabled: Boolean,
    colors: ButtonColors,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier.drawWithContent {
            drawContent()
            drawNeonStroke(indentationSize, DangerColor)
        },
        shape = shape,
        border = BorderStroke(1.dp, Color.Transparent),
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        enabled = enabled,
        colors = colors,
    ) {
        ProvideTextStyle(TextStyle(color = DangerColor)) {
            content()
        }
    }
}

@Preview
@Composable
private fun DangerOutlinedFishButtonPreview() {
    FishMonstersTheme {
        Surface {
            Column(modifier = Modifier.padding(20.dp)) {
                DangerOutlinedFishButton(onClick = { }) {
                    CapText(text = "Default indentationSize")
                }
                Spacer(modifier = Modifier.height(20.dp))
                DangerOutlinedFishButton(
                    onClick = { },
                    settingsGlobalState = SettingsGlobalState(neonStyles = true)
                ) {
                    CapText(text = "Default indentationSize")
                }
            }
        }
    }
}