package com.fish.monsters.core.navigation

import android.util.Log
import androidx.navigation.NavHostController
import com.fish.monsters.common.extensions.putArgument

/**
 * This is navigation handler, All navigation should be done through [Navigator].
 *
 * Steps to add your screen to navigation:
 * * Add new screen object to [Screen], provide route and argument key if you need
 * * Add composable nav entry to [AppNavHost]
 *
 * **USE**
 *
 * To use [Navigator] you need to inject it in parameter to your composable using [org.koin.compose.koinInject]
 * Then pass Navigator() in your Preview Composable
 *
 * see [HomePage Example][com.fish.monsters.features.home.HomeScreen]
 *
 * **WARNING**: Don't access [Navigator] by its constructor if you are not in Preview mode!
 */
class Navigator {
    private lateinit var navController: NavHostController

    fun setNavController(navController: NavHostController) {
        this.navController = navController
    }

    /**
     * Navigates to given [Screen].
     *
     * @throws IllegalArgumentException if the given [screen] is invalid
     * @see AppNavHost
     * @see Screen
     */
    fun navigateTo(screen: Screen) {
        navController.navigate(screen.route)
    }

    /**
     * Same as [navigateTo] but with [String] argument. There is only one argument allowed for now.
     * Do nothing if there is no argument keys for given [Screen]
     *
     * @see Screen.MainGameScreen
     * @see AppNavHost
     */
    fun navigateTo(screen: Screen, argument: String) {
        if (screen.argumentKeys.isNotEmpty()) navController.navigate(
            screen.route.putArgument(
                screen.argumentKeys[0], argument
            )
        )
        else logNavError("No argument keys found, are you sure you set them in your Screen object?")
    }

    /**
     * Navigates back to the previous screen. If the current screen is the only one in the stack, the application will close.
     */
    fun popBackStack() {
        navController.popBackStack()
    }

    /**
     * Navigates back to specific [Screen]. If the [Screen] does not exist in the current stack, nothing happens
     */
    fun popBackStack(screen: Screen) {
        if (navController.contains(screen)) navController.popBackStack(screen.route, false)
        else logNavError("Given screen not found in currentBackStack")
    }

    private fun NavHostController.contains(screen: Screen): Boolean {
        this.currentBackStack.value.map { it.destination.route }.let {
            return it.contains(screen.route)
        }
    }

    private fun logNavError(message: String) {
        Log.e("NAVIGATOR_ERROR: ", message)
    }
}