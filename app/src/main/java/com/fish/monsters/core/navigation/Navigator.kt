package com.fish.monsters.core.navigation

import android.util.Log
import androidx.navigation.NavHostController
import com.fish.monsters.common.extensions.putArgument

/**
 * This is a navigation handler. All navigation should be performed through [Navigator].
 *
 * Steps to add your screen to navigation:
 * * Add a new screen object to [Screen], providing a route and an argument key if necessary.
 * * Add a composable navigation entry to [AppNavHost].
 *
 * **USAGE**
 *
 * To use [Navigator], you need to inject it as a parameter into your composable using [org.koin.compose.koinInject].
 * Then, pass `Navigator()` in your Preview Composable.
 *
 * See [HomePage Example][com.fish.monsters.features.home.HomeScreen].
 *
 * **WARNING**: Do not access [Navigator] via its constructor unless you are in Preview mode!
 */
class Navigator {
    private lateinit var navController: NavHostController

    fun setNavController(navController: NavHostController) {
        this.navController = navController
    }

    /**
     * Navigates to the given [Screen].
     *
     * @throws IllegalArgumentException if the given [screen] is invalid.
     * @see AppNavHost
     * @see Screen
     */
    fun navigateTo(screen: Screen) {
        navController.navigate(screen.route)
    }

    /**
     * Same as [navigateTo] but with a [String] argument. Only one argument is allowed for now.
     * Does nothing if there are no argument keys for the given [Screen].
     *
     * @see Screen.MainGameScreen
     * @see AppNavHost
     */
    fun navigateTo(screen: Screen, argument: String) {
        if (screen.argumentKeys.isNotEmpty()) {
            navController.navigate(
                screen.route.putArgument(screen.argumentKeys[0], argument)
            )
        } else {
            logNavError("No argument keys found. Are you sure you have set them in your Screen object?")
        }
    }

    /**
     * Navigates back to the previous screen. If the current screen is the only one in the stack, the application will close.
     */
    fun popBackStack() {
        navController.popBackStack()
    }

    /**
     * Navigates back to a specific [Screen]. If the [Screen] does not exist in the current stack, nothing happens.
     */
    fun popBackStack(screen: Screen) {
        if (navController.contains(screen)) {
            navController.popBackStack(screen.route, false)
        } else {
            logNavError("Given screen not found in the current backstack.")
        }
    }

    private fun NavHostController.contains(screen: Screen): Boolean {
        return this.currentBackStack.value.map { it.destination.route }.contains(screen.route)
    }

    private fun logNavError(message: String) {
        Log.e("NAVIGATOR_ERROR: ", message)
    }
}
