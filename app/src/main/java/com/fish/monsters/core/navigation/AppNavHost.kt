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
 * Declares all possible navigation routes in the application along with their arguments
 *
 * **How to add a screen?**
 * * Without arguments: add to [NavGraphBuilder] the following:
 * ```
 *      composable(Screen.YourScreen.route) {
 *          YourScreen()
 *      }
 * ```
 *  * With arguments: add a function to [NavGraphBuilder] similar to the one
 *  above, but this time with argument handling:
 *  ```
 *       composable(Screen.YourScreen.route) { backStackEntry ->
 *          backStackEntry.arguments?.getString(Screen.YourScreen.argumentKeys[0])?.let {
 *              YourScreen(it)
 *          }
 *      }
 *  ```
 *
 *  **WARNING** It is your responsibility to convert argument from/to String if you need
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