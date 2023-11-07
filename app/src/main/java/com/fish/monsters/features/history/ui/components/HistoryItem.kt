package com.fish.monsters.features.history.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fish.monsters.common.views.PreviewContainer

@Composable
fun HistoryItem(contestData: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = contestData)
    }
}

@Preview
@Composable
fun SettingsRowSectionPreview() {
    PreviewContainer {
        HistoryItem(contestData = "Some label")
    }
}