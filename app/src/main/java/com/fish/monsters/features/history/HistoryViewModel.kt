package com.fish.monsters.features.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fish.monsters.core.database.dao.ContestDao
import kotlinx.coroutines.launch

class HistoryViewModel(private val contestDao: ContestDao) : ViewModel() {
    private val _allContestsState = mutableStateOf(HistoryState())
    val allContestsState: State<HistoryState> = _allContestsState

    private val _selectedContestDetailsState = mutableStateOf(HistoryState())
    val selectedContestDetailsState: State<HistoryState> = _selectedContestDetailsState

    init {
        getAllContests()
    }

    private fun getAllContests() {
        viewModelScope.launch {
            val allContests = contestDao.getAllContests()
            _allContestsState.value = _allContestsState.value.copy(contests = allContests)
        }
    }

    fun getContestDetailsById(contestId: Long) {
        viewModelScope.launch {
            val contestDetails = contestDao.getContestDetailsById(contestId)
            _selectedContestDetailsState.value = _selectedContestDetailsState.value.copy(selectedContestDetails = contestDetails)
        }
    }
}
