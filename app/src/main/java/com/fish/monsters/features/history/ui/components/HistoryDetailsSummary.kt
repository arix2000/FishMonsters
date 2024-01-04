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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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

@Composable
fun HistoryDetailsSummary(contestDetails: Contest) {
        Column {
            HistoryDetailsSummaryRow(
                contestDetails = contestDetails,
                isFirst = true,
                detailTypeImageVector = Icons.Default.CalendarMonth,
                imageContentDescription = "Calendar Icon",
                detailTypeNameResourceId = R.string.date,
                valueColText = contestDetails.date
            )
            HistoryDetailsSummaryRow(
                contestDetails = contestDetails,
                detailTypeImageVector = Icons.Default.AccessTime,
                imageContentDescription = "Clock Icon",
                detailTypeNameResourceId = R.string.survival_time,
                valueColText = contestDetails.duration.toString(includeSeconds = true)
            )
            HistoryDetailsSummaryRow(
                contestDetails = contestDetails,
                detailTypeImageVector = Icons.Default.SportsScore,
                imageContentDescription = "Score Icon",
                detailTypeNameResourceId = R.string.points_scored,
                valueColText = contestDetails.points.toString()
            )
            HistoryDetailsSummaryRow(
                contestDetails = contestDetails,
                detailTypeImageVector = Icons.Outlined.VideogameAsset,
                imageContentDescription = "Difficulty Icon",
                detailTypeNameResourceId = R.string.difficulty_level,
                valueColText = stringResource(id = contestDetails.difficultyLevel.stringRes),
                valueColTextColor = contestDetails.difficultyLevel.color
            )
            HistoryDetailsSummaryRow(
                contestDetails = contestDetails,
                detailTypeImageVector = Icons.Outlined.WineBar,
                imageContentDescription = "Reward Icon",
                detailTypeNameResourceId = R.string.awards_won,
            )
            HistoryDetailsSummaryRow(
                contestDetails = contestDetails,
                imageContentDescription = "Monsters Icon",
                detailTypeNameResourceId = R.string.avoided_creatures,
                valueColText = contestDetails.bypassedMonsters.toString(),
            )
        }
    }

@Composable
private fun HistoryDetailsSummaryRow(contestDetails: Contest,
                                     isFirst: Boolean = false,
                                     detailTypeImageVector: ImageVector? = null,
                                     imageContentDescription: String,
                                     detailTypeNameResourceId: Int,
                                     valueColText: String = "",
                                     valueColTextColor: Color = DarkPrimaryColor,
                                     ) {
    if (!isFirst) {
        Spacer(modifier = Modifier.height(12.dp))
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (detailTypeImageVector != null) {
                    Icon(
                        imageVector = detailTypeImageVector,
                        contentDescription = imageContentDescription,
                        tint = DarkPrimaryColor,
                        modifier = Modifier.width(17.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.fish_monter_icon),
                        contentDescription = "Monsters Icon",
                        modifier = Modifier.width(17.dp)
                    )
                }
                Text(
                    text = " " + stringResource(id = detailTypeNameResourceId),
                    color = DarkPrimaryColor,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        Column {
            if (valueColText != "") {
                Text(
                    text = valueColText,
                    color = valueColTextColor,
                    fontWeight = FontWeight.SemiBold
                )
            } else {
                Row {
                    for (awardCount in contestDetails.awardsEarned) {
                        if (awardCount.count > 0) {
                            AwardCol(awardCount = awardCount)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AwardCol(awardCount: Award) {
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
fun HistoryDetailsScreenContentPreview() {
    PreviewContainer {
        HistoryDetailsSummary(
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
                    Award(AwardType.Flower, 1),
                    Award(AwardType.Pumpkin, 2)
                ),
                isGameSuccess = true,
                gameLocation = GameLocation(
                    52.41653257428317, 16.931677800000003
                )
            )
        )
    }
}