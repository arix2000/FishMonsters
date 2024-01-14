package com.fish.monsters.features.game.presentation.ui

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import com.fish.monsters.R
import com.fish.monsters.common.views.DefaultLoadingScreen
import com.fish.monsters.core.navigation.Navigator
import com.fish.monsters.core.navigation.Screen
import com.fish.monsters.features.game.models.Difficulty
import com.fish.monsters.features.game.presentation.MainGameEvent
import com.fish.monsters.features.game.presentation.MainGameViewModel
import com.fish.monsters.features.game.presentation.ui.map.MainGameMap
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import org.koin.compose.koinInject

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainGameScreen(
    difficulty: Difficulty,
    viewModel: MainGameViewModel = koinInject(),
    navigator: Navigator = koinInject()
) {
    val state = viewModel.state.value
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    LaunchedEffect(true) {
        locationPermissionState.launchPermissionRequest()
    }
    when {
        state.isLoading -> DefaultLoadingScreen()
        state.userLocation != null -> MainGameMap(
            difficulty,
            state,
            onGameOver = { navigator.popBackStack(Screen.HomeScreen) }
        ) { viewModel.invokeEvent(it) }

        locationPermissionState.status.isGranted -> LaunchedEffect(true) {
            viewModel.invokeEvent(MainGameEvent.ListenOnUserLocationChanges)
        }

        locationPermissionState.status.shouldShowRationale ->
            PermissionDeniedDialog(titleText = stringResource(R.string.permission_denied),
                contentText = stringResource(R.string.permission_denied_content_text),
                onConfirm = { navigator.popBackStack(Screen.StartScreen) })
    }
}