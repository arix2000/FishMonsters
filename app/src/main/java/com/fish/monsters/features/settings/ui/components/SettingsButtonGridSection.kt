package com.fish.monsters.features.settings.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fish.monsters.R
import com.fish.monsters.common.views.CapText
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.common.views.buttons.OutlinedFishButton

@Composable
fun SettingsButtonGridSection(
    onReportProblemClicked: () -> Unit,
    onHowToPlayClicked: () -> Unit,
    onRateUsClicked: () -> Unit,
    onSupportClicked: () -> Unit,
) {
    val spacing = remember { 8.dp }
    Column(Modifier.fillMaxWidth()) {
        Row {
            OutlinedFishButton(modifier = Modifier.weight(1f), onClick = onReportProblemClicked) {
                CapText(text = stringResource(R.string.report_problem))
            }
            Spacer(modifier = Modifier.width(spacing))
            OutlinedFishButton(modifier = Modifier.weight(1f), onClick = onHowToPlayClicked) {
                CapText(text = stringResource(R.string.how_to_play))
            }
        }
        Spacer(modifier = Modifier.height(spacing))
        Row {
            OutlinedFishButton(modifier = Modifier.weight(1f), onClick = onRateUsClicked) {
                CapText(text = stringResource(R.string.rate_us))
            }
            Spacer(modifier = Modifier.width(spacing))
            OutlinedFishButton(modifier = Modifier.weight(1f), onClick = onSupportClicked) {
                CapText(text = stringResource(R.string.support))
            }
        }
    }
}

@Preview
@Composable
private fun SettingsButtonGridSectionPreview() {
    PreviewContainer(modifier = Modifier.padding(20.dp)) {
        SettingsButtonGridSection({}, {}, {}, {})
    }
}