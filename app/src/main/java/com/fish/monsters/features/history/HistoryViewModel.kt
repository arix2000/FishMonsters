package com.fish.monsters.features.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fish.monsters.core.database.dao.ContestDao
import kotlinx.coroutines.launch

class HistoryViewModel(private val contestDao: ContestDao) : ViewModel() {
    private val _historyState = mutableStateOf(HistoryState())
    val historyState: State<HistoryState> = _historyState

    fun getAllContests() {
        viewModelScope.launch {
            val allContests = contestDao.getAllContests()
            _historyState.value = _historyState.value.copy(contests = allContests)
        }
    }

    fun getContestDetailsById(contestId: Long) {
        viewModelScope.launch {
            val contestDetails = contestDao.getContestDetailsById(contestId)
            _historyState.value = _historyState.value.copy(selectedContest = contestDetails)
        }
    }
}
