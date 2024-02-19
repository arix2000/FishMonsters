package com.fish.monsters.features.game.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.fish.monsters.R
import com.fish.monsters.common.views.CapText
import com.fish.monsters.common.views.FishDialog
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.common.views.buttons.FishButton

@Composable
fun PermissionDeniedDialog(
    titleText: String,
    contentText: String,
    onConfirm: () -> Unit
) {
    FishDialog(
        onDismissRequest = {},
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {
        ProvideTextStyle(TextStyle(textAlign = TextAlign.Center)) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = titleText, fontSize = 25.sp)
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = contentText)
                Spacer(modifier = Modifier.height(16.dp))
                FishButton(modifier = Modifier.align(Alignment.End), onClick = { onConfirm() }) {
                    CapText(text = stringResource(android.R.string.ok))
                }
            }
        }
    }
}

@Preview
@Composable
private fun PermissionDeniedDialogPreview() {
    PreviewContainer(modifier = Modifier.fillMaxSize()) {
        PermissionDeniedDialog(
            titleText = stringResource(R.string.permission_denied),
            contentText = stringResource(R.string.permission_denied_content_text),
            onConfirm = { }
        )
    }
}