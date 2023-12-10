package com.fish.monsters.features.history.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.core.database.entities.Contest
import com.fish.monsters.core.database.entities.contest.Award
import com.fish.monsters.core.database.entities.contest.AwardsCount
import com.fish.monsters.core.database.entities.contest.DifficultyLevel
import com.fish.monsters.core.database.entities.contest.Duration
import com.fish.monsters.core.database.entities.contest.Enhancement
import com.fish.monsters.core.database.entities.contest.GameLocation
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.features.history.HistoryViewModel
import org.koin.compose.koinInject

@Composable
fun HistoryDetailsScreen(contestId: Long, viewModel: HistoryViewModel = koinInject()) {
    viewModel.getContestDetailsById(contestId)

    val contestDetailsState = remember(viewModel.selectedContestDetailsState) {
        viewModel.selectedContestDetailsState
    }

    val contestDetails = contestDetailsState.value

    ScreenBox(title = "Contest Details") {
        HistoryDetailsScreenContent(contestDetails.selectedContestDetails)
    }
}

@Composable
fun HistoryDetailsScreenContent(contestDetails: Contest?) {
    Box(modifier = Modifier.fillMaxSize())
    {
    if (contestDetails != null) {
        Text(text = "HISTORY DETAILS $contestDetails", color = DarkPrimaryColor)
    } else {
        Text(text = "No contest selected", color = DarkPrimaryColor)
    }}
}

@Preview
@Composable
fun HistoryDetailsScreenPreview() {
    HistoryDetailsScreenContent(Contest(
        id = 4,
        date = "10 march 2024",
        duration = Duration(1, 15, 40),
        points = 75,
        difficultyLevel = DifficultyLevel.MEDIUM,
        rewardsCount = 12,
        enhancementsUsed = listOf(
            Enhancement(
                name = "Enhancement 4",
                time = Duration(1, 30, 12)
            )
        ),
        bypassedMonsters = 1,
        awardsEarned = listOf(
            AwardsCount(Award.Grass, 2),
            AwardsCount(Award.Flower, 1),
            AwardsCount(Award.Pumpkin,1)
        ),
        isGameSuccess = true,
        gameLocation = GameLocation(
            latitude = 25.0,
            longitude = 20.0
        )
    ))
}
