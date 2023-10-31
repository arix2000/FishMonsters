package com.fish.monsters.features.settings.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fish.monsters.R
import com.fish.monsters.common.views.CapText
import com.fish.monsters.common.views.FishDialog
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.common.views.buttons.DangerOutlinedFishButton
import com.fish.monsters.common.views.buttons.OutlinedFishButton
import com.fish.monsters.core.theme.DangerColor

@Composable
fun SettingsClearProgressDialog(onDismissRequest: () -> Unit, onAcceptClicked: () -> Unit) {
    FishDialog(
        onDismissRequest = onDismissRequest,
        borderColor = DangerColor,
    ) {
        Column {
            Text(
                text = stringResource(R.string.settings_clear_progress_dialog_title),
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.settings_clear_progress_dialog_content_text),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedFishButton(onClick = onDismissRequest) {
                    CapText(text = stringResource(R.string.cancel))
                }
                DangerOutlinedFishButton(onClick = onAcceptClicked) {
                    CapText(text = stringResource(R.string.settings_clear_progress_dialog_accept_button_text))
                }
            }
        }
    }
}

@Preview
@Composable
private fun SettingsClearProgressDialogPreview() {
    PreviewContainer(modifier = Modifier.fillMaxSize()) {
        SettingsClearProgressDialog({}, {})
    }
}