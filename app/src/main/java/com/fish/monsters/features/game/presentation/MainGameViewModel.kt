package com.fish.monsters.features.game.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fish.monsters.features.game.utils.LocationService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainGameViewModel(private val locationService: LocationService) : ViewModel() {
    private var _state = mutableStateOf(MainGameState())
    val state: State<MainGameState> get() = _state

    fun invokeEvent(event: MainGameEvent) {
        when (event) {
            MainGameEvent.ListenOnUserLocationChanges -> listenOnUserLocationChanged()
            MainGameEvent.ListenOnTime -> listenOnTime()
        }
    }

    private fun listenOnUserLocationChanged() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            locationService.requestLocationUpdates().collect { location ->
                _state.value = _state.value.copy(userLocation = location, isLoading = false)
            }
        }
    }

    private fun listenOnTime() {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                _state.apply {
                    value = value.copy(timeSeconds = value.timeSeconds + 1)
                }
            }
        }
    }

}