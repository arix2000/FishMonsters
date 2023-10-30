package com.fish.monsters.common.views.screenContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fish.monsters.common.extensions.isPreview
import com.fish.monsters.core.navigation.Navigator
import com.fish.monsters.core.theme.FishMonstersTheme
import org.koin.compose.koinInject

@Composable
fun ScreenBox(
    title: String,
    navigator: Navigator = if (isPreview()) Navigator() else koinInject(),
    onBackButtonClicked: ((Navigator) -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit,
) {
    Column {
        FishTopBar(title, navigator, onBackButtonClicked = onBackButtonClicked, content = content)
        Box(content = content)
    }
}

@Preview
@Composable
private fun ScreenViewPreview() {
    FishMonstersTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ScreenBox(title = "Wybierz poziom trudności") { Text(text = "Hello Screen view") }
        }
    }
}