package com.fish.monsters.common.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.fish.monsters.common.extensions.drawNeonStroke
import com.fish.monsters.common.extensions.isPreview
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.common.utils.settings.SettingsManager
import com.fish.monsters.common.views.buttons.FishButton
import com.fish.monsters.common.views.buttons.OutlinedFishButton
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.theme.SurfaceColor
import org.koin.compose.koinInject

@Composable
fun FishDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit,
    borderColor: Color = DarkPrimaryColor,
    neonStyle: Boolean = if (isPreview()) false else koinInject<SettingsManager>().state.value.neonStyles,
    properties: DialogProperties = DialogProperties(usePlatformDefaultWidth = false),
    content: @Composable () -> Unit
) {
    val defaultDialogShape = remember {
        PartiallyCutCornerShape(DpSize(25.dp, 50.dp))
    }
    Dialog(onDismissRequest, properties) {
        Box(
            modifier = modifier
                .fillMaxWidth(0.92f)
                .drawWithContent {
                    drawContent()
                    if (neonStyle)
                        drawNeonStroke(defaultDialogShape.indentationSize, borderColor)
                }
                .border(1.dp, borderColor, defaultDialogShape)
                .background(SurfaceColor, shape = defaultDialogShape)
                .padding(horizontal = 25.dp, vertical = 15.dp)
        ) {
            content()
        }
    }
}

@Preview
@Composable
private fun FishDialogPreview() {
    PreviewContainer(modifier = Modifier.fillMaxSize()) {
        Text(text = LoremIpsum(200).values.joinToString())
        FishDialog(onDismissRequest = {}) {
            Column {
                Text(text = LoremIpsum(2).values.joinToString(), fontSize = 25.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = LoremIpsum(25).values.joinToString())
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedFishButton(onClick = {}) {
                        Text(text = "Anuluj")
                    }
                    FishButton(onClick = { }) {
                        Text(text = "Potwierdź")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun FishDialogNeonStylePreview() {
    PreviewContainer(modifier = Modifier.fillMaxSize()) {
        Text(text = LoremIpsum(200).values.joinToString())
        FishDialog(onDismissRequest = {}, neonStyle = true) {
            Column {
                Text(text = LoremIpsum(2).values.joinToString(), fontSize = 25.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = LoremIpsum(25).values.joinToString())
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedFishButton(onClick = {}) {
                        Text(text = "Anuluj")
                    }
                    FishButton(onClick = { }) {
                        Text(text = "Potwierdź")
                    }
                }
            }
        }
    }
}