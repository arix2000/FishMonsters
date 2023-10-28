package com.fish.monsters.features.history

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fish.monsters.R
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.core.theme.FishMonstersTheme

@Composable
fun HistoryScreen() {
    ScreenBox(title = stringResource(id = R.string.history)) {

    }
}

@Preview
@Composable
fun HistoryScreenPreview() {
    FishMonstersTheme {
        Surface() {
            HistoryScreen()
        }
    }
}