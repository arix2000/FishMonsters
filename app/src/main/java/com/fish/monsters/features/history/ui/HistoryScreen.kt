package com.fish.monsters.features.history.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fish.monsters.R
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.core.theme.FishMonstersTheme

@Composable
fun HistoryScreen() {
    HistoryScreenContent()
}

@Composable
fun HistoryScreenContent() {
    ScreenBox(title = stringResource(id = R.string.history)) {
        Column(
            Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 30.dp)
        ) {

        }
    }
}

@Preview
@Composable
private fun HistoryScreenPreview() {
    FishMonstersTheme {
        Surface() {
            HistoryScreen()
        }
    }
}