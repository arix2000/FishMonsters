package com.fish.monsters.features.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fish.monsters.R
import com.fish.monsters.common.extensions.toPercentString
import com.fish.monsters.common.views.CapText
import com.fish.monsters.common.views.FadingHorizontalDivider
import com.fish.monsters.common.views.FishSlider
import com.fish.monsters.common.views.FishSwitch
import com.fish.monsters.common.views.buttons.DangerOutlinedFishButton
import com.fish.monsters.common.views.buttons.DropdownTextButton
import com.fish.monsters.common.views.buttons.OutlinedFishButton
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.core.theme.FishMonstersTheme


@Composable
fun SettingsScreen() {
    SettingsScreenContent()
}

@Composable
fun SettingsScreenContent() {
    ScreenBox(title = stringResource(id = R.string.settings)) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 30.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            RowSection(label = "Język: ") {
                DropdownTextButton(items = listOf("Polski", "English"), onItemClicked = {})
            }
            RowSection(label = "Wibracje") {
                FishSwitch(checked = true, onCheckedChange = {})
            }
            SliderSection(label = "Muzyka", defaultValue = 0.5f, onValueChange = {})
            SliderSection(label = "Dźwięk", defaultValue = 0.5f, onValueChange = {})
            FadingHorizontalDivider()
            RowSection(label = "Neonowy styl przycisków") {
                FishSwitch(checked = false, onCheckedChange = {})
            }
            ButtonsGridSection()
            DangerOutlinedFishButton(onClick = { }, modifier = Modifier.fillMaxWidth()) {
                CapText(text = "Wyczyść postęp")
            }
        }
    }
}

@Composable
private fun RowSection(label: String, content: @Composable RowScope.() -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, fontSize = 18.sp)
        content()
    }
}

@Composable
private fun SliderSection(label: String, defaultValue: Float, onValueChange: (Float) -> Unit) {
    var sliderValue by remember {
        mutableFloatStateOf(defaultValue)
    }
    Column {
        Text(text = label, fontSize = 18.sp)
        FishSlider(value = sliderValue, onValueChange = {
            sliderValue = it
            onValueChange(it)
        })
        Text(
            text = sliderValue.toPercentString(),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal
        )
    }
}

@Composable
private fun ButtonsGridSection() {
    Column(Modifier.fillMaxWidth()) {
        Row {
            OutlinedFishButton(onClick = { }, modifier = Modifier.weight(1f)) {
                CapText(text = "Zgłoś problem")
            }
            Spacer(modifier = Modifier.width(6.dp))
            OutlinedFishButton(onClick = { }, modifier = Modifier.weight(1f)) {
                CapText(text = "Jak grać?")
            }
        }
        Row {
            OutlinedFishButton(onClick = { }, modifier = Modifier.weight(1f)) {
                CapText(text = "Oceń nas")
            }
            Spacer(modifier = Modifier.width(6.dp))
            OutlinedFishButton(onClick = { }, modifier = Modifier.weight(1f)) {
                CapText(text = "Wspieraj")
            }
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    FishMonstersTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box {
                SettingsScreenContent()
            }
        }
    }
}