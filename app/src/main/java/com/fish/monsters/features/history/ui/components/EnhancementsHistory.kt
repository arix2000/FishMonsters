package com.fish.monsters.features.history.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fish.monsters.R
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.core.database.entities.Contest
import com.fish.monsters.core.database.entities.contest.Award
import com.fish.monsters.core.database.entities.contest.AwardType
import com.fish.monsters.core.database.entities.contest.DifficultyLevel
import com.fish.monsters.core.database.entities.contest.Duration
import com.fish.monsters.core.database.entities.contest.Enhancement
import com.fish.monsters.core.database.entities.contest.GameLocation
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.features.history.ui.spaceFromTime

@Composable
fun EnhancementsHistory(contestDetails: Contest) {
    Spacer(modifier = Modifier.height(24.dp))
    Row(
        modifier = Modifier
            .padding(0.dp)
            .height(30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.buff_icon),
            contentDescription = "Buff Icon",
            Modifier.size(20.dp),
            tint = DarkPrimaryColor
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = stringResource(id = R.string.enhancements_history),
            fontSize = 20.sp
        )
    }
    Spacer(modifier = Modifier.height(12.dp))
    Column(modifier = Modifier.fillMaxWidth(0.95f)) {
        HistoryStartEndRow(
            title = stringResource(id = R.string.history_game_start),
            isEndRow = false
        )

        var previousTimeInSeconds = 0

        contestDetails.enhancementsUsed.forEach { enhancement ->
            val timeInSeconds = enhancement.time.toSeconds()
            val space = spaceFromTime(timeInSeconds - previousTimeInSeconds)
            EnhancementHistoryItem(
                enhancement = enhancement,
                height = space
            )
            previousTimeInSeconds = timeInSeconds
        }

        HistoryStartEndRow(
            title = stringResource(id = R.string.history_game_end),
            isEndRow = true,
            spacerHeight = spaceFromTime(contestDetails.duration.toSeconds() - previousTimeInSeconds)
        )
    }
}

@Preview
@Composable
fun EnhancementsHistoryPreview() {
    PreviewContainer {
        EnhancementsHistory(
            contestDetails = Contest(
                date = "08 November 2023",
                duration = Duration(2, 30, 12),
                points = 85,
                difficultyLevel = DifficultyLevel.LOW,
                rewardsCount = 10,
                enhancementsUsed = listOf(
                    Enhancement(
                        name = "good_winds",
                        time = Duration(0, 25, 0)
                    ),
                    Enhancement(
                        name = "quiet_zone",
                        time = Duration(0, 55, 0)
                    ),
                    Enhancement(
                        name = "kraken_urine",
                        time = Duration(2, 0, 15)
                    )
                ),
                bypassedMonsters = 0,
                awardsEarned = listOf(
                    Award(AwardType.Flower, 1)
                ),
                isGameSuccess = true,
                gameLocation = GameLocation(
                    52.41653257428317, 16.931677800000003
                )
            )
        )
    }
}
