package com.fish.monsters.features.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fish.monsters.core.database.dao.ContestDao
import kotlinx.coroutines.launch

class HistoryViewModel(private val contestDao: ContestDao): ViewModel(){
    private val _state = mutableStateOf(HistoryState())
    val state: State<HistoryState> = _state
    init {
        getAllContest()
    }

    private fun getAllContest() {
        viewModelScope.launch {
            _state.value = _state.value.copy(contests = contestDao.getAllContests())
        }
    }
}