package com.fish.monsters.common.views

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun DefaultLoadingScreen() {
    Box(contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}