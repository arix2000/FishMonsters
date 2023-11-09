package com.fish.monsters.features.history.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fish.monsters.R
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.core.database.dao.ContestDao
import com.fish.monsters.core.database.entities.ContestInfoEntity
import com.fish.monsters.core.theme.FishMonstersTheme
import com.fish.monsters.features.contest.ContestInfo
import com.fish.monsters.features.contest.DifficultyLevel
import com.fish.monsters.features.contest.Duration
import com.fish.monsters.features.history.ui.components.HistoryItem

@Composable
fun HistoryScreen(contestDao: ContestDao) {
    val contestInfoList: List<ContestInfoEntity> = contestDao.getAllContestInfo()

    ScreenBox(title = stringResource(id = R.string.history)) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp, top = 30.dp)
        ) {
            contestInfoList.forEach { contestInfo ->
                HistoryItem(contestInfo)
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

@Preview
@Composable
private fun HistoryScreenPreviewWithData() {
    val contestInfoList = listOf(
        ContestInfoEntity(
            id = 1,
            date = "08 ${stringResource(R.string.november)} 2023",
            duration = Duration(2, 30),
            points = 85,
            difficultyLevel = DifficultyLevel.LOW
        ),
        ContestInfoEntity(
            id = 2,
            date = "15 ${stringResource(R.string.january)} 2024",
            duration = Duration(1, 45),
            points = 92,
            difficultyLevel = DifficultyLevel.MEDIUM
        ),
        ContestInfoEntity(
            id = 3,
            date = "20 ${stringResource(R.string.june)} 2025",
            duration = Duration(3, 15),
            points = 78,
            difficultyLevel = DifficultyLevel.HIGH
        )
    )
    FishMonstersTheme {
        Surface() {
            HistoryScreen(object : ContestDao {
                override suspend fun getAllContestInfo(): List<ContestInfoEntity> {
                    return contestInfoList
                }
            })
        }
    }
}
