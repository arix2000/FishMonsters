package com.fish.monsters.core.navigation

import com.fish.monsters.common.extensions.withArgument

/**
 * All screens in Fish Monsters app
 *
 * **WARNING** [Screen]s declared here are not equal your Composable screen but should have the same name
 * @param _route It should be the same as the object name
 * @param argumentKeys Optional, it should only have one argument key describing what will be passed to your screen
 *
 **/
sealed class Screen(
    private var _route: String,
    vararg val argumentKeys: String = emptyArray(),
) {
    val route: String
        get() {
            var finalRoute = _route
            argumentKeys.forEach { argumentKey ->
                finalRoute = finalRoute.withArgument(argumentKey)
            }
            return finalRoute
        }

    object HomeScreen : Screen("HomeScreen")

    object StartScreen : Screen("StartScreen")

    object SettingsScreen : Screen("SettingsScreen")

    object HistoryScreen : Screen("HistoryScreen")

    object AboutScreen : Screen("AboutScreen")

    object MainGameScreen : Screen("MainGameScreen", "difficulty")
}
