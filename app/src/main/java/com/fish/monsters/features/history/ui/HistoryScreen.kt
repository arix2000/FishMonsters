package com.fish.monsters.features.history.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fish.monsters.R
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.core.database.entities.Contest
import com.fish.monsters.core.database.entities.contest.AwardType
import com.fish.monsters.core.database.entities.contest.Award
import com.fish.monsters.core.theme.FishMonstersTheme
import com.fish.monsters.core.database.entities.contest.DifficultyLevel
import com.fish.monsters.core.database.entities.contest.Duration
import com.fish.monsters.core.database.entities.contest.Enhancement
import com.fish.monsters.core.database.entities.contest.GameLocation
import com.fish.monsters.features.history.HistoryViewModel
import com.fish.monsters.features.history.ui.components.HistoryEmptyScreen
import com.fish.monsters.features.history.ui.components.HistoryItem
import org.koin.compose.koinInject

@Composable
fun HistoryScreen(viewModel: HistoryViewModel = koinInject()) {
    LaunchedEffect(true) {
        viewModel.getAllContests()
    }
    val state = viewModel.historyState.value
    ScreenBox(title = stringResource(id = R.string.history)) {
        HistoryScreenContent(state.contests)
    }
}

@Composable
fun HistoryScreenContent(contests: List<Contest>) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        if (contests.isEmpty()) {
            Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                HistoryEmptyScreen()
            }
        } else {
            LazyColumn(modifier = Modifier.padding(top = 15.dp)) {
                items(contests) { contest ->
                    HistoryItem(contest)
                }
            }
        }
    }
}

@Preview
@Composable
private fun HistoryScreenPreviewWithData() {
    val contestInfoList = listOf(
        Contest(
            id = 4,
            date = "10 March 2024",
            duration = Duration(1, 15, 40),
            points = 75,
            difficultyLevel = DifficultyLevel.MEDIUM,
            rewardsCount = 12,
            enhancementsUsed = listOf(
                Enhancement(
                    name = "Enhancement 4",
                    time = Duration(1, 30, 0)
                )
            ),
            bypassedMonsters = 1,
            awardsEarned = listOf(
                Award(AwardType.Grass, 1)
            ),
            isGameSuccess = true,
            gameLocation = GameLocation(
                latitude = 25.0,
                longitude = 20.0
            )
        ),
        Contest(
            id = 5,
            date = "05 July 2023",
            duration = Duration(2, 0, 15),
            points = 88,
            difficultyLevel = DifficultyLevel.HIGH,
            rewardsCount = 10,
            enhancementsUsed = listOf(
                Enhancement(
                    name = "Enhancement 5",
                    time = Duration(0, 45, 0)
                ),
                Enhancement(
                    name = "Enhancement 6",
                    time = Duration(2, 30, 0)
                )
            ),
            bypassedMonsters = 3,
            awardsEarned = listOf(
                Award(AwardType.Flower, 1),
                Award(AwardType.Pumpkin, 1)
            ),
            isGameSuccess = false,
            gameLocation = GameLocation(
                latitude = 15.0,
                longitude = 10.0
            )
        ),
        Contest(
            id = 6,
            date = "18 April 2024",
            duration = Duration(1, 45, 0),
            points = 95,
            difficultyLevel = DifficultyLevel.LOW,
            rewardsCount = 15,
            enhancementsUsed = emptyList(),
            bypassedMonsters = 0,
            awardsEarned = listOf(
                Award(AwardType.Flower, 1),
                Award(AwardType.Grass, 1),
                Award(AwardType.Pumpkin, 1)
            ),
            isGameSuccess = true,
            gameLocation = GameLocation(
                latitude = 35.0,
                longitude = 30.0
            )
        )
    )

    FishMonstersTheme {
        Surface() {
            HistoryScreenContent(contestInfoList)
        }
    }
}
