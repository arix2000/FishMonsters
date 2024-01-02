package com.fish.monsters.features.history.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.SportsScore
import androidx.compose.material.icons.outlined.VideogameAsset
import androidx.compose.material.icons.outlined.WineBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fish.monsters.R
import com.fish.monsters.common.views.PreviewContainer
import com.fish.monsters.core.database.entities.Contest
import com.fish.monsters.core.database.entities.contest.Award
import com.fish.monsters.core.database.entities.contest.AwardsCount
import com.fish.monsters.core.database.entities.contest.DifficultyLevel
import com.fish.monsters.core.database.entities.contest.Duration
import com.fish.monsters.core.database.entities.contest.Enhancement
import com.fish.monsters.core.database.entities.contest.GameLocation
import com.fish.monsters.core.theme.DarkPrimaryColor

@Composable
fun HistoryDetailsSummary(contestDetails: Contest?) {
    if (contestDetails != null) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column() {
                    Row {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = "Calendar Icon",
                            tint = DarkPrimaryColor,
                            modifier = Modifier.width(17.dp)
                        )
                        Text(
                            text = " " + stringResource(id = R.string.date),
                            color = DarkPrimaryColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Column {
                    Text(
                        text = contestDetails.date,
                        color = DarkPrimaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.AccessTime,
                            contentDescription = "Clock Icon",
                            tint = DarkPrimaryColor,
                            modifier = Modifier.width(17.dp)
                        )
                        Text(
                            text = " " + stringResource(id = R.string.survival_time),
                            color = DarkPrimaryColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Column {
                    Text(
                        text = contestDetails.duration.toString(includeSeconds = true),
                        color = DarkPrimaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.SportsScore,
                            contentDescription = "Score Icon",
                            tint = DarkPrimaryColor,
                            modifier = Modifier.width(17.dp)
                        )
                        Text(
                            text = " " + stringResource(id = R.string.points_scored),
                            color = DarkPrimaryColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Column {
                    Text(
                        text = contestDetails.points.toString(),
                        color = DarkPrimaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.VideogameAsset,
                            contentDescription = "Difficulty Icon",
                            tint = DarkPrimaryColor,
                            modifier = Modifier.width(17.dp)
                        )
                        Text(
                            text = " " + stringResource(id = R.string.difficulty_level),
                            color = DarkPrimaryColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Column {
                    Text(
                        text = stringResource(id = contestDetails.difficultyLevel.stringRes),
                        color = contestDetails.difficultyLevel.color,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.WineBar,
                            contentDescription = "Reward Icon",
                            tint = DarkPrimaryColor,
                            modifier = Modifier.width(17.dp)
                        )
                        Text(
                            text = " " + stringResource(id = R.string.awards_won),
                            color = DarkPrimaryColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Column {
                    Row {
                        for (awardCount in contestDetails.awardsEarned) {
                            if (awardCount.count > 0) {
                                AwardCol(awardCount = awardCount)
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(id = R.drawable.fish_monter_icon),
                            contentDescription = "Monsters Icon",
                            modifier = Modifier.width(17.dp)
                        )
                        Text(
                            text = " " + stringResource(id = R.string.avoided_creatures),
                            color = DarkPrimaryColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                Column {
                    Text(
                        text = contestDetails.bypassedMonsters.toString(),
                        color = DarkPrimaryColor,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Composable
fun AwardCol(awardCount: AwardsCount) {
    Column {
        Text(
            text = " ${awardCount.count}x",
            color = DarkPrimaryColor,
            fontWeight = FontWeight.SemiBold
        )
    }
    Column {
        Image(
            painter = painterResource(id = awardCount.getIconForAward(award = awardCount.award)),
            contentDescription = "Award Icon",
            modifier = Modifier.width(17.dp)
        )
    }
}

@Preview
@Composable
fun HistoryDetailsSummaryPreview() {
    PreviewContainer {
        HistoryDetailsSummary(contestDetails = Contest(
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
        ))
    }
}
