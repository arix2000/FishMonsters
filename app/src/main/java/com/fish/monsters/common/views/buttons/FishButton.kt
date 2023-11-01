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
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Shape
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
import com.fish.monsters.core.theme.FishMonstersTheme
import com.fish.monsters.core.theme.TextColorDark
import org.koin.compose.koinInject

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FishButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    indentationSize: DpSize = DpSize(12.dp, 24.dp),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabled: Boolean = true,
    settingsGlobalState: SettingsGlobalState = if (isPreview()) SettingsGlobalState() else koinInject<SettingsManager>().state.value,
    content: @Composable (RowScope.() -> Unit)
) {
    val localView = LocalView.current
    val soundManager: SoundsManager = if (isPreview()) previewGetSoundsManager() else koinInject()
    CompositionLocalProvider(
        LocalMinimumTouchTargetEnforcement provides false,
    ) {
        if (settingsGlobalState.neonStyles) FishButtonNeonStyle(
            onClick = {
                soundManager.playDefaultButtonSound()
                localView.performVibration(settingsGlobalState.vibration)
                onClick()
            },
            modifier = modifier,
            shape = PartiallyCutCornerShape(indentationSize),
            contentPadding = contentPadding,
            border = border,
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(),
            enabled = enabled,
            indentationSize = indentationSize,
            content = {
                ProvideTextStyle(TextStyle(color = TextColorDark)) {
                    content()
                }
            },
        )
        else Button(
            onClick = {
                soundManager.playDefaultButtonSound()
                localView.performVibration(settingsGlobalState.vibration)
                onClick()
            },
            modifier = modifier,
            shape = PartiallyCutCornerShape(indentationSize),
            border = border,
            contentPadding = contentPadding,
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(),
            enabled = enabled,
            content = {
                ProvideTextStyle(TextStyle(color = TextColorDark)) {
                    content()
                }
            },
        )
    }
}

@Composable
private fun FishButtonNeonStyle(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    shape: Shape,
    indentationSize: DpSize,
    border: BorderStroke? = null,
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
        border = border,
        contentPadding = contentPadding,
        interactionSource = interactionSource,
        enabled = enabled,
        colors = colors,
        content = content,
    )
}

@Preview
@Composable
private fun FishButtonPreview() {
    FishMonstersTheme {
        Box(modifier = Modifier.padding(20.dp)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                FishButton(modifier = Modifier.fillMaxWidth(), onClick = {}) {
                    Text(text = "Default indentationSize")
                }
                FishButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    indentationSize = DpSize(10.dp, 10.dp)
                ) {
                    Text(text = "indentationSize = DpSize(10.dp, 10.dp)")
                }
                FishButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    indentationSize = DpSize(12.dp, 30.dp),
                    contentPadding = PaddingValues(13.dp)
                ) {
                    Text(text = "indentationSize = DpSize(12.dp, 30.dp)")
                }
                Text(text = "Neon style true: ")
                FishButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    settingsGlobalState = SettingsGlobalState(neonStyles = true)
                ) {
                    Text(text = "Default indentationSize")
                }
                FishButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    indentationSize = DpSize(10.dp, 10.dp),
                    settingsGlobalState = SettingsGlobalState(neonStyles = true)
                ) {
                    Text(text = "indentationSize = DpSize(10.dp, 10.dp)")
                }
                FishButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    indentationSize = DpSize(12.dp, 30.dp),
                    contentPadding = PaddingValues(13.dp),
                    settingsGlobalState = SettingsGlobalState(neonStyles = true)
                ) {
                    Text(text = "indentationSize = DpSize(12.dp, 30.dp)")
                }
            }

        }
    }
}