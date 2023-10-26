package com.fish.monsters.core.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fish.monsters.features.about.AboutScreen
import com.fish.monsters.features.game.MainGameScreen
import com.fish.monsters.features.game.models.Difficulty
import com.fish.monsters.features.history.HistoryScreen
import com.fish.monsters.features.home.HomeScreen
import com.fish.monsters.features.settings.SettingsScreen
import com.fish.monsters.features.start.StartScreen
import org.koin.compose.koinInject

/**
 * Defines all possible navigation routes within the application, along with their associated arguments.
 *
 * **How to add a screen?**
 * - Without arguments: Add the following to the [NavGraphBuilder]:
 * ```
 * composable(Screen.YourScreen.route) {
 *     YourScreen()
 * }
 * ```
 * - With arguments: Add a function to the [NavGraphBuilder] similar to the one above, but this time, include argument handling:
 * ```
 * composable(Screen.YourScreen.route) { backStackEntry ->
 *     backStackEntry.arguments?.getString(Screen.YourScreen.argumentKeys[0])?.let {
 *         YourScreen(it)
 *     }
 * }
 * ```
 *
 * **WARNING**: It is your responsibility to handle the conversion of arguments to and from String if necessary.
 */

@Composable
fun AppNavHost() {
    val navController = rememberNavController().apply {
        koinInject<Navigator>().setNavController(this)
    }
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        enterTransition = { fadeIn(animationSpec = tween(200)) },
        exitTransition = { fadeOut(animationSpec = tween(200)) },
    ) {
        composable(Screen.HomeScreen.route) {
            HomeScreen()
        }
        composable(Screen.StartScreen.route) {
            StartScreen()
        }
        composable(Screen.SettingsScreen.route) {
            SettingsScreen()
        }
        composable(Screen.HistoryScreen.route) {
            HistoryScreen()
        }
        composable(Screen.AboutScreen.route) {
            AboutScreen()
        }
        composable(Screen.MainGameScreen.route) { backStackEntry ->
            backStackEntry.arguments?.getString(Screen.MainGameScreen.argumentKeys[0])?.let {
                MainGameScreen(Difficulty.valueOf(it))
            }
        }
    }
}
