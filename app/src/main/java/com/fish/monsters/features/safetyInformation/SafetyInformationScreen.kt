package com.fish.monsters.features.safetyInformation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fish.monsters.R
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.common.views.SafetyInformationCard
import com.fish.monsters.common.views.buttons.FishButton
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.core.navigation.Navigator
import com.fish.monsters.core.navigation.Screen
import com.fish.monsters.core.theme.TextColorLight
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@Composable
fun SafetyInformationScreen(
    checkboxState: Boolean,
    navigator: Navigator,
    preferencesManager: PreferencesManager
) {
    var showAgain by remember { mutableStateOf(checkboxState) }

    ScreenBox(title = stringResource(R.string.safety_information)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, top = 30.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Spacer(modifier = Modifier.height(60.dp))

            Text(
                text = stringResource(id = R.string.safety_warning),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = TextColorLight
            )

            SafetyInformationCard(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = R.string.safety_info_text),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(all = 20.dp)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Checkbox(
                    checked = showAgain,
                    onCheckedChange = {
                        showAgain = it
                        CoroutineScope(Dispatchers.IO).launch {
                            preferencesManager.setShowSafetyScreen(it)
                        }
                    }
                )
                Spacer(Modifier.width(8.dp))
                Text(text = stringResource(id = R.string.do_not_show_again))
            }

            Spacer(modifier = Modifier.weight(1f))

            FishButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                onClick = {
                    navigator.navigateTo(Screen.MainGameScreen)
                }

            ) {
                Text(
                    text = stringResource(id = R.string.start),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

class MockPreferencesManager : PreferencesManager(null) {
    override suspend fun setShowSafetyScreen(show: Boolean) {
    }

    override val showSafetyScreenFlow: Flow<Boolean> = flowOf(true)
}

class MockNavigator : Navigator() {

    override fun navigateTo(screen: Screen) {
    }

    override fun navigateTo(screen: Screen, argument: String) {
    }
}

@Preview(showBackground = true)
@Composable
private fun SafetyInformationScreenPreview() {
    PreviewContainer {
        SafetyInformationScreen(
            checkboxState = false,
            navigator = MockNavigator(),
            preferencesManager = MockPreferencesManager()
        )
    }
}


