package com.fish.monsters.common.views

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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.core.theme.FishMonstersTheme
import com.fish.monsters.core.theme.TextColorDark

@Composable
fun FishButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    indentationSize: DpSize = DpSize(12.dp, 24.dp),
    border: BorderStroke? = null,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
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

@Preview
@Composable
fun FishButtonPreview() {
    FishMonstersTheme {
        Box(modifier = Modifier.padding(20.dp)) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                FishButton(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Default indentationSize")
                }
                FishButton(
                    onClick = {},
                    indentationSize = DpSize(10.dp, 10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "indentationSize = DpSize(10.dp, 10.dp)")
                }
                FishButton(
                    onClick = {},
                    indentationSize = DpSize(12.dp, 30.dp),
                    contentPadding = PaddingValues(13.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "indentationSize = DpSize(12.dp, 30.dp)")
                }
            }

        }
    }
}