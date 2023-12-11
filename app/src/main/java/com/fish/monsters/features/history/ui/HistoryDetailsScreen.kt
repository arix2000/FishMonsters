package com.fish.monsters.features.history.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fish.monsters.R
import com.fish.monsters.common.shapes.PartiallyCutCornerShape
import com.fish.monsters.common.views.screenContent.ScreenBox
import com.fish.monsters.core.database.entities.Contest
import com.fish.monsters.core.database.entities.contest.Award
import com.fish.monsters.core.database.entities.contest.AwardsCount
import com.fish.monsters.core.database.entities.contest.DifficultyLevel
import com.fish.monsters.core.database.entities.contest.Duration
import com.fish.monsters.core.database.entities.contest.Enhancement
import com.fish.monsters.core.database.entities.contest.GameLocation
import com.fish.monsters.core.theme.DangerColor
import com.fish.monsters.core.theme.DarkPrimaryColorA12
import com.fish.monsters.core.theme.EasyColor
import com.fish.monsters.core.theme.LightPrimaryColor
import com.fish.monsters.features.history.HistoryViewModel
import com.fish.monsters.features.history.ui.components.HistoryDetailsSummary
import org.koin.compose.koinInject

@Composable
fun HistoryDetailsScreen(contestId: Long, viewModel: HistoryViewModel = koinInject()) {
    viewModel.getContestDetailsById(contestId)

    val contestDetailsState = remember(viewModel.selectedContestDetailsState) {
        viewModel.selectedContestDetailsState
    }

    val contestDetails = contestDetailsState.value

    ScreenBox(title = stringResource(id = R.string.contest_details)) {
        HistoryDetailsScreenContent(contestDetails.selectedContestDetails)
    }
}

@Composable
fun HistoryDetailsScreenContent(
    contestDetails: Contest?,
    shape: PartiallyCutCornerShape = PartiallyCutCornerShape(
        DpSize(12.dp, 31.dp)
    ),
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        if (contestDetails != null) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(74.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.contest_result),
                    color = LightPrimaryColor,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
                if (contestDetails.isGameSuccess) {
                    Text(
                        text = " " + stringResource(id = R.string.success),
                        color = EasyColor,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                } else {
                    Text(
                        text = " " + stringResource(id = R.string.failure),
                        color = DangerColor,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 9.dp)
                    .background(DarkPrimaryColorA12, shape)
                    .padding(vertical = 28.dp),
            ) {
                HistoryDetailsSummary(contestDetails)
            }
        }
    }
}

@Preview
@Composable
fun HistoryDetailsScreenPreview() {
    HistoryDetailsScreenContent(
        Contest(
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
            bypassedMonsters = 18,
            awardsEarned = listOf(
                AwardsCount(Award.Grass, 2),
                AwardsCount(Award.Flower, 12),
                AwardsCount(Award.Pumpkin, 1)
            ),
            isGameSuccess = true,
            gameLocation = GameLocation(
                latitude = 25.0,
                longitude = 20.0
            )
        )
    )
}
