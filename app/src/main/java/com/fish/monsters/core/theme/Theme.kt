package com.fish.monsters.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val darkColorScheme = darkColorScheme(
    primary = DarkPrimaryColor,
    secondary = DarkPrimaryColor,
    tertiary = TextColorDark
)

private val lightColorScheme = lightColorScheme(
    primary = LightPrimaryColor,
    secondary = LightPrimaryColor,
)

@Composable
fun FishMonstersTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(darkColorScheme.surface)
    MaterialTheme(
        colorScheme = darkColorScheme,
        typography = Typography,
        content = content
    )
}