package com.fish.monsters.features.history.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DoNotDisturbOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fish.monsters.R
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.common.views.buttons.OutlinedFishButton
import com.fish.monsters.core.navigation.Navigator
import com.fish.monsters.core.navigation.Screen
import com.fish.monsters.core.theme.DarkPrimaryColor
import org.koin.compose.koinInject

@Composable
fun HistoryEmptyScreen(navigator: Navigator = koinInject()) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .size(74.dp)
                .padding(7.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.fillMaxSize(),
                imageVector = Icons.Outlined.DoNotDisturbOn,
                contentDescription = "Do Not Disturb Icon",
                tint = DarkPrimaryColor
            )
        }
        Box(
            modifier = Modifier
                .width(278.dp)
                .height(72.dp)
        ) {
            Text(
                text = stringResource(id = R.string.empty_history_info),
                color = DarkPrimaryColor,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.height(14.dp))
        Box(
            modifier = Modifier
                .height(36.dp)
                .width(212.dp)
        ){
            OutlinedFishButton(
                modifier = Modifier.fillMaxSize(),
                onClick = { navigator.navigateTo(Screen.StartScreen) },
            ) {
                Text(
                    text = stringResource(id = R.string.empty_history_start_button),
                    color = DarkPrimaryColor
                )
            }
        }
    }
}

@Preview
@Composable
private fun HistoryEmptyScreenPreview() {
    PreviewContainer {
        HistoryEmptyScreen(Navigator())
    }
}
