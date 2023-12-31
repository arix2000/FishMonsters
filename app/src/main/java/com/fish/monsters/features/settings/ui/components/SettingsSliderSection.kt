package com.fish.monsters.features.settings.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.fish.monsters.common.extensions.fromPercents
import com.fish.monsters.common.extensions.toPercents
import com.fish.monsters.common.views.FishSlider
import com.fish.monsters.common.views.PreviewContainer

@Composable
fun SettingsSliderSection(
    label: String,
    value: Int,
    onValueChange: (Int) -> Unit,
    onValueChangeFinished: () -> Unit
) {
    Column {
        Text(text = label, fontSize = 18.sp)
        FishSlider(value = Float.fromPercents(value), onValueChange = {
            onValueChange(it.toPercents())
        }, onValueChangeFinished = onValueChangeFinished)
        Text(
            text = value.toString(),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal
        )
    }
}

@Preview
@Composable
fun SettingsSliderSectionPreview() {
    PreviewContainer {
        SettingsSliderSection(
            label = "Label",
            value = 39,
            onValueChange = {},
            onValueChangeFinished = {})
    }
}