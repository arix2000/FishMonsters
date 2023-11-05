package com.fish.monsters.common.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.fish.monsters.core.theme.FishMonstersTheme

@Composable
fun PreviewContainer(modifier: Modifier = Modifier, content: @Composable BoxScope.() -> Unit) {
    FishMonstersTheme {
        Surface {
            Box(modifier) {
                content()
            }
        }
    }
}