package com.fish.monsters.features.history.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.SportsScore
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.fish.monsters.R
import com.fish.monsters.common.extensions.drawNeonStroke
import com.fish.monsters.common.extensions.isPreview
import com.fish.monsters.common.utils.settings.SettingsManager
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.common.views.buttons.OutlinedFishButton
import com.fish.monsters.core.database.entities.Contest
import com.fish.monsters.core.database.entities.contest.Award
import com.fish.monsters.core.theme.DarkPrimaryColor
import com.fish.monsters.core.database.entities.contest.DifficultyLevel
import com.fish.monsters.core.database.entities.contest.Duration
import com.fish.monsters.core.database.entities.contest.GameLocation
import org.koin.compose.koinInject

@Composable
fun HistoryItem(
    contestInfo: Contest,
    neonStyle: Boolean = if (isPreview()) false else koinInject<SettingsManager>().state.value.neonStyles,
) {
    OutlinedFishButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .drawWithContent {
                drawContent()
                if (neonStyle)
                    drawNeonStroke(DpSize(12.dp, 31.dp), DarkPrimaryColor)
            },
        onClick = { println(contestInfo) },
        indentationSize = DpSize(12.dp, 31.dp),
        contentPadding = PaddingValues(13.dp, 14.dp),
    ) {
        Row(Modifier.fillMaxSize()) {
            Column(Modifier.width(200.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "Calendar Icon",
                        tint = DarkPrimaryColor,
                        modifier = Modifier.width(16.dp)
                    )
                    Text(text = contestInfo.date, Modifier.padding(3.dp, 0.dp))
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                )
                Row {
                    val difficultyStringRes = when (contestInfo.difficultyLevel) {
                        DifficultyLevel.LOW -> R.string.low_difficulty
                        DifficultyLevel.MEDIUM -> R.string.medium_difficulty
                        DifficultyLevel.HIGH -> R.string.high_difficulty
                    }
                    Text(
                        text = stringResource(id = difficultyStringRes),
                        color = contestInfo.difficultyLevel.color
                    )
                }
            }
            Column(Modifier.width(200.dp), horizontalAlignment = Alignment.End) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.SportsScore,
                        contentDescription = "Score Icon",
                        tint = DarkPrimaryColor,
                        modifier = Modifier.width(18.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(text = contestInfo.points.toString())
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.AccessTime,
                        contentDescription = "Time Icon",
                        tint = DarkPrimaryColor,
                        modifier = Modifier.width(14.dp)
                    )
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = buildAnnotatedString {
                            if (contestInfo.duration.hours > 0) {
                                append("${contestInfo.duration.hours}h ")
                            }
                            append("${contestInfo.duration.minutes}min")
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HistoryItemPreview() {
    PreviewContainer {
        val contestInfo = Contest(
            id = 6,
            date = "18 ${stringResource(R.string.april)} 2024",
            duration = Duration(1, 45, 0),
            points = 95,
            difficultyLevel = DifficultyLevel.LOW,
            rewardsCount = 15,
            enhancementsUsed = emptyList(),
            bypassedMonsters = 0,
            awardsEarned = listOf(
                Award(
                    name = "Award 10"
                ),
                Award(
                    name = "Award 11"
                ),
                Award(
                    name = "Award 12"
                )
            ),
            isGameSuccess = true,
            gameLocation = GameLocation(
                latitude = 35.0,
                longitude = 30.0
            )
        )
        HistoryItem(contestInfo)
    }
}
